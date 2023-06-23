import api.SyncCallAdapterFactory;
import api.auth.Token;
import api.genres.Genre;
import api.tracks.Track;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Set;
import java.util.stream.Collectors;

public class SpotifyApi {
    private final Retrofit retrofit;
    private final ServiceManager serviceManager;

    public SpotifyApi(Token token, boolean enableLogging) {
        OkHttpClient.Builder clientWithAuthHeader = new OkHttpClient().newBuilder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    request = request.newBuilder()
                            .addHeader("Authorization", "Bearer " + token.getAccessToken())
                            .build();
                    return chain.proceed(request);
                });

        if (enableLogging) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientWithAuthHeader.addInterceptor(logging);
        }

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.spotify.com")
                .addPathSegment("v1")
                .addPathSegment("") // creates trailing slash
                .build();

        Gson gson = new Gson().newBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(SyncCallAdapterFactory.create())
                .client(clientWithAuthHeader.build())
                .build();

        this.serviceManager = new ServiceManager(retrofit);
    }

    public Set<Genre> getGenres() {
        return serviceManager.getGenreService().getGenres().response()
                .getGenres()
                .stream().map(Genre::new)
                .collect(Collectors.toSet());
    }

    public Track getTrackById(String id) {
        return serviceManager.getTrackService().getTrack(id).response();
    }

}
