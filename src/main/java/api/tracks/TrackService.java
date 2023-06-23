package api.tracks;

import api.SyncCall;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TrackService {
    @GET("tracks/{id}")
    SyncCall<Track> getTrack(@Path("id") String id); //TODO change Track to TrackResponse

    @GET("tracks/{id}")
    SyncCall<Track> getTrack(@Path("id") String id,
                             @Query("market") String market);

    @GET("tracks")
    SyncCall<TracksResponse> getTracks(@Query("ids") String ids);

    @GET("tracks")
    SyncCall<TracksResponse> getTracks(@Query("ids") String ids,
                                   @Query("market") String market);

    //TODO Check/Remove/Save/Get User's Saved Tracks (user auth)

    @GET("audio-features")
    SyncCall<AudioFeaturesResponse> getAudioFeatures(@Query("ids") String ids);
}
