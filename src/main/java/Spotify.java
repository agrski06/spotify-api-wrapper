import api.auth.CredentialsClient;
import api.auth.Credentials;
import api.auth.Token;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import lombok.Data;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@Data
public class Spotify {
    private Token token;
    private SpotifyApi spotifyApi;

    private Spotify(String clientId, String clientSecret, boolean enableLogging) {
        // Spotify API seems to be using this naming strategy
        Gson gson = new Gson().newBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl("https://accounts.spotify.com/")
                .addConverterFactory(GsonConverterFactory.create(gson));

        if (enableLogging) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(logging).build();
            retrofitBuilder.client(client);
        }

        Retrofit retrofit = retrofitBuilder.build();
        Credentials credentials = new Credentials(clientId, clientSecret);
        CredentialsClient credentialsClient = retrofit.create(CredentialsClient.class);

        Call<Token> tokenCall = credentialsClient.getToken(credentials.getClientId(), credentials.getClientSecret(), credentials.getGrantType());

        try {
            this.token = tokenCall.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        spotifyApi = new SpotifyApi(token);
    }

    public static class Builder {
        private String clientId;
        private String clientSecret;
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

        public Spotify build() {
            return new Spotify(clientId, clientSecret, enableHttpRequestLogging);
        }
    }

}
