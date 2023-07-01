package api.albums;

import api.SyncCall;
import api.tracks.TrackPage;
import retrofit2.http.*;

import java.util.Set;

public interface AlbumService {
    @GET("albums/{id}")
    SyncCall<Album> getAlbum(@Path("id") String id,
                             @Query("market") String market);

    @GET("albums")
    SyncCall<Set<Album>> getAlbums(@Query("ids") String ids,
                                   @Query("market") String market);

    @GET("albums/{id}/tracks")
    SyncCall<TrackPage> getAlbumTracks(@Path("id") String id,
                                       @Query("market") String market,
                                       @Query("limit") Integer limit,
                                       @Query("offset") Integer offset);

    /**
     * Requires user-library-read
     */
    @GET("me/albums")
    SyncCall<AlbumPage> getUsersAlbums(@Query("limit") Integer limit,
                                       @Query("offset") Integer offset,
                                       @Query("market") String market);

    /**
     * Requires user-library-modify
     */
    @PUT("me/albums")
    SyncCall<Void> saveAlbumsForUser(@Query("ids") String ids,
                                     @Body SaveAlbumRequest request);

    /**
     * Requires user-library-modify
     */
    @DELETE("me/albums")
    SyncCall<Void> deleteAlbumsFromUser(@Query("ids") String ids,
                                     @Body RemoveAlbumRequest request);

    /**
     * Requires user-library-read
     */
    @GET("me/albums/contains")
    SyncCall<Set<Boolean>> checkIfUserHasAlbumsSaved(@Query("ids") String ids);

    @GET("browse/new-releases")
    SyncCall<NewReleasesResponse> getNewReleases(@Query("country") String country,
                                       @Query("limit") Integer limit,
                                       @Query("offset") Integer offset);
}
