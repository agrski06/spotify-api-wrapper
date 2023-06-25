import api.SyncCallAdapterFactory;
import api.auth.AuthToken;
import api.auth.CredentialsClient;
import api.auth.Credentials;
import api.auth.Token;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import lombok.Data;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Base64;

@Data
public class Spotify {
    private Token token;
    private AuthToken authToken;
    private SpotifyApi<? extends Token> spotifyApi;

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
            this.token = credentialsClient.getToken(credentials.getClientId(),
                    credentials.getClientSecret(),
                    credentials.getGrantType()).response();
            spotifyApi = new SpotifyApi<>(this.token, enableLogging);
        } else {
            String authHeader = "Basic " + Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
            this.authToken = credentialsClient.getAuthToken(authHeader, "authorization_code", code, redirectUri).response();
            spotifyApi = new SpotifyApi<>(this.authToken, enableLogging);
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
                .getToken(clientId, "code", redirectUri, state, scope, showDialog)
                .request().url().toString();
    }

    public static class Builder {
        private String clientId;
        private String clientSecret;
        private String code;
        private String redirectUri;
        private boolean enableHttpRequestLogging = false;

        public Builder createDefault(String clientId, String clientSecret) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
            return this;
        }

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

        public Spotify build() {
            return new Spotify(clientId, clientSecret, code, redirectUri, enableHttpRequestLogging);
        }

    }

}
