import api.SyncCallAdapterFactory;
import api.auth.*;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import lombok.Data;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class Spotify {
    private Token token;
    private AuthToken authToken;
    private SpotifyApi spotifyApi;
    private HashSet<AuthScope> authScopes = new HashSet<>();
    private AuthType authType;

    /**
     * Constructor used for client_credentials auth flow
     * @param clientId registered app clientId
     * @param clientSecret registered app clientSecret
     * @param enableLogging enable http requests logging
     */
    private Spotify(String clientId, String clientSecret, String code, String redirectUri, boolean enableLogging) {
        Retrofit retrofit = getRetrofit(enableLogging);
        Credentials credentials = new Credentials(clientId, clientSecret);
        CredentialsClient credentialsClient = retrofit.create(CredentialsClient.class);

        if (code == null || code.isBlank() || redirectUri == null || redirectUri.isBlank()) {
            this.authType = AuthType.CLIENT_CREDENTIALS;
            this.token = credentialsClient.getToken(credentials.getClientId(),
                    credentials.getClientSecret(),
                    credentials.getGrantType()).response();
            spotifyApi = new SpotifyApi(this.token, enableLogging);
        } else {
            this.authType = AuthType.AUTH_CODE;
            String authHeader = "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
            this.authToken = credentialsClient.getAuthToken(authHeader, "authorization_code", code, redirectUri).response();
            if (this.authToken.getScope() != null) {
                this.authScopes = Arrays.stream(this.authToken.getScope().split(" "))
                        .map(AuthScope::fromString)
                        .collect(Collectors.toCollection(HashSet::new));
            }
            spotifyApi = new SpotifyApi(this.authToken, enableLogging);
        }
    }

    private static Retrofit getRetrofit(boolean enableLogging) {
        // Spotify API seems to be using this naming strategy
        Gson gson = new Gson().newBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl("https://accounts.spotify.com/")
                .addCallAdapterFactory(SyncCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));

        if (enableLogging) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(logging).build();
            retrofitBuilder.client(client);
        }

        return retrofitBuilder.build();
    }

    /**
     * Generate URI needed for "Authorization Code" Authentication Flow
     */
    public String generateAuthUri(String clientId, String redirectUri, String state, String scope, boolean showDialog) {
        if (clientId == null || redirectUri == null) {
            throw new RuntimeException("ClientId and RedirectURI cannot be null!");
        }
        Retrofit retrofit = getRetrofit(false);
        return retrofit.create(CredentialsClient.class)
                .getToken(clientId, "code", redirectUri, state, scope.isBlank() ? null : scope, showDialog)
                .request().url().toString();
    }

    /**
     * Generate URI needed for "Authorization Code" Authentication Flow
     */
    public String generateAuthUri(String clientId, String redirectUri, String state, Set<AuthScope> scopes, boolean showDialog) {
        if (clientId == null || redirectUri == null) {
            throw new RuntimeException("ClientId and RedirectURI cannot be null!");
        }
        String scopeString = String.join(",", scopes.stream().map(AuthScope::toString).toList());
        Retrofit retrofit = getRetrofit(false);
        return retrofit.create(CredentialsClient.class)
                .getToken(clientId, "code", redirectUri, state, scopeString, showDialog)
                .request().url().toString();
    }

    public static class Builder {
        private String clientId;
        private String clientSecret;
        private String code;
        private String redirectUri;
        private boolean enableHttpRequestLogging = false;

        public Builder setClientId(String clientId) {
            this.clientId = clientId;
            return this;
        }

        public Builder setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
            return this;
        }

        public Builder enableHttpRequestLogging() {
            this.enableHttpRequestLogging = true;
            return this;
        }

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder setRedirectUri(String redirectUri) {
            this.redirectUri = redirectUri;
            return this;
        }

        // TODO: maybe create create different constructors, so that there are no two same checks
        public Spotify buildAuthCodeFlow() {
            if (clientId == null || clientSecret == null) {
                throw new RuntimeException("Cannot create Authorization Code Flow; clientId=" + clientId + " clientSecret=" + clientSecret);
            }
            return new Spotify(clientId, clientSecret, code, redirectUri, enableHttpRequestLogging);
        }

        public Spotify buildClientCredentialsFlow() {
            return new Spotify(clientId, clientSecret, null, null, enableHttpRequestLogging);
        }

        /**
         * Automatically checks provided fields and chooses appropriate Auth Flow
         * @return Spotify instance
         */
        public Spotify build() {
            return new Spotify(clientId, clientSecret, code, redirectUri, enableHttpRequestLogging);
        }

    }

}
