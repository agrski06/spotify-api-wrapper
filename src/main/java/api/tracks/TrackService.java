package api.tracks;

import api.SyncCall;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TrackService {
    @GET("tracks/{id}")
    SyncCall<Track> getTrack(@Path("id") String id);

    @GET("tracks/{id}")
    SyncCall<Track> getTrack(@Path("id") String id,
                             @Query("market") String market);
}
