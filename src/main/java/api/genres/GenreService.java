package api.genres;

import api.SyncCall;
import retrofit2.http.GET;

public interface GenreService {
    @GET("recommendations/available-genre-seeds")
    SyncCall<GenreResponse> getGenres();
}
