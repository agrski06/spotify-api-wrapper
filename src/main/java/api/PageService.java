package api;

import api.artists.ArtistPageResponse;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface PageService {
    @GET()
    SyncCall<ArtistPageResponse> getArtistPage(@Url String url);
}
