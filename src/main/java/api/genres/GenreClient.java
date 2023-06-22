package api.genres;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GenreClient {
    @GET("recommendations/available-genre-seeds")
    Call<GenreResponse> getGenres();
}
