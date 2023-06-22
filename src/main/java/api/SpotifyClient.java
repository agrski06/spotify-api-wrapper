package api;

import api.genres.Genre;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import java.util.concurrent.CompletableFuture;

public interface SpotifyClient {
    @GET("/recommendations/available-genre-seeds")
    @Headers("")
    CompletableFuture<Genre> getGenres();
}
