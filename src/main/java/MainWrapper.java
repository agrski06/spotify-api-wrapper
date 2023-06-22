import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainWrapper {
    private OkHttpClient clientWithApiKey(String apiKey) {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request originalRequest = chain.request();
                    originalRequest.newBuilder().addHeader("Authorization", "Bearer " + apiKey);
                    HttpUrl newUrl = originalRequest.url().newBuilder()
                            .addQueryParameter("api_key", apiKey).build();
                    Request request = originalRequest.newBuilder().url(newUrl).build();
                    return chain.proceed(request);
                }).build();
    }

    public static void main(String[] args) {
        String clientId = args[0];
        String clientSecret = args[1];

        Spotify spotify = new Spotify.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();

        System.out.println(spotify.getToken());
    }
}
