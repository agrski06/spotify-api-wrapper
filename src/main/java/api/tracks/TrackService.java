package api.tracks;

import api.SyncCall;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TrackService {
    @GET("tracks/{id}")
    SyncCall<Track> getTrack(@Path("id") String id);
}
