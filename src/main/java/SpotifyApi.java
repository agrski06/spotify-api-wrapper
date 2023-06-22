import api.SimpleCallAdapterFactory;
import api.auth.Token;
import api.genres.Genre;
import api.genres.GenreService;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.stream.Collectors;

public class SpotifyApi {
    private final Retrofit retrofit;
    private static GenreService genreService;

    public SpotifyApi(Token token) {
        OkHttpClient clientWithAuthHeader = new OkHttpClient().newBuilder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    request = request.newBuilder()
                            .addHeader("Authorization", "Bearer " + token.getAccessToken())
                            .build();
                    return chain.proceed(request);
                }).build();
        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.spotify.com")
                .addPathSegment("v1")
                .addPathSegment("")
                .build();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new CallAdapter.Factory() {
                    @Override
                    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
                        return null;
                    }
                })
                .addCallAdapterFactory(SimpleCallAdapterFactory.create())
                .client(clientWithAuthHeader)
                .build();
    }

    public Set<Genre> getGenres() {
        if (genreService == null) {
            genreService = retrofit.create(GenreService.class);
        }
        return genreService.getGenres().response()
                .getGenres()
                .stream().map(Genre::new)
                .collect(Collectors.toSet());
    }
}
