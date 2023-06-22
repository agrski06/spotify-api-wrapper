import api.auth.Token;
import api.genres.Genre;
import api.genres.GenreClient;
import api.genres.GenreResponse;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

public class SpotifyApi {

    private final Retrofit retrofit;
    private static GenreClient genreClient;

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
                .client(clientWithAuthHeader)
                .build();
    }

    public Set<Genre> getGenres() {
        if (genreClient == null) {
            genreClient = retrofit.create(GenreClient.class);
        }
        Call<GenreResponse> genresRequest = genreClient.getGenres();
        try {
            Response<GenreResponse> genresResponse = genresRequest.execute();
            if (genresResponse.body() == null)
                return null;
            return genresResponse.body().getGenres()
                    .stream().map(Genre::new).collect(Collectors.toSet());
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }
}
