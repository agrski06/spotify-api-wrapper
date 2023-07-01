package api.artists;

import api.SyncCall;
import api.albums.AlbumPage;
import api.tracks.TracksResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ArtistService {
    @GET("artists/{id}")
    SyncCall<Artist> getArtist(@Path("id") String id);

    @GET("artists")
    SyncCall<ArtistsResponse> getArtists(@Query("ids") String ids);

    @GET("artists")
    SyncCall<AlbumPage> getArtistAlbums(@Query("id") String id,
                                        @Query("include_groups") String includeGroups,
                                        @Query("market") String market,
                                        @Query("limit") Integer limit,
                                        @Query("offset") Integer offset);

    @GET("artists/{id}/top-tracks")
    SyncCall<TracksResponse> getArtistTopTracks(@Path("id") String id,
                                                @Query("market") String market);

    @GET("artists/{id}/related-artists")
    SyncCall<ArtistsResponse> getRelatedArtists(@Path("id") String id);
}
