package api.genres;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.Set;

public interface GenreClient {
    @GET("/recommendations/available-genre-seeds")
    Call<Set<Genre>> getGenres();
}
