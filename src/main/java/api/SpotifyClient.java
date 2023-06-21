package requester;

import requester.models.Genre;
import retrofit2.http.GET;

import java.util.concurrent.CompletableFuture;

public interface SpotifyClient {
    @GET("/recommendations/available-genre-seeds")
    CompletableFuture<Genre> getGenres();
}
