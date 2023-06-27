package api.tracks;

import api.SyncCall;
import retrofit2.http.*;

import java.util.Map;
import java.util.Set;

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

    /**
     * Get a list of the songs saved in the current Spotify user's 'Your Music' library.
     * Requires user-library-read scope
     */
    @GET("me/tracks")
    SyncCall<SavedTracksResponse> getSavedTracks(@Query("market") String market,
                                                 @Query("limit") Integer limit,
                                                 @Query("offset") Integer offset);

    /**
     * Save one or more tracks to the current user's 'Your Music' library.
     * Requires user-library-modify scope
     */
    @PUT("me/tracks")
    SyncCall<Void> saveTracksForCurrentUser(@Body TracksRequest request);

    /**
     * Remove one or more tracks from the current user's 'Your Music' library.
     * Requires user-library-modify scope
     */
    @DELETE("me/tracks")
    SyncCall<Void> deleteTracksForCurrentUser(@Body TracksRequest request);

    /**
     * Check if one or more tracks is already saved in the current Spotify user's 'Your Music' library.
     * Requires user-library-read scope
     */
    @GET("me/tracks/contains")
    SyncCall<Set<Boolean>> checkTracksForCurrentUser(@Query("ids") String ids);

    @GET("audio-features")
    SyncCall<AudioFeaturesResponse> getAudioFeaturesForTracks(@Query("ids") String ids);

    @GET("audio-features/{id}")
    SyncCall<AudioFeatures> getAudioFeaturesForTrack(@Path("id") String id);

    @GET("audio-analysis/{id}")
    SyncCall<AudioAnalysis> getAudioAnalysis(@Path("id") String id);

    @GET("recommendations")
    SyncCall<Recommendation> getRecommendation(@QueryMap Map<String, String> filters);

    @GET("recommendations")
    SyncCall<Recommendation> getRecommendation(@Query("limit") Integer limit,
                                               @Query("market") String market,
                                               @Query("seed_artists") String seedArtists,
                                               @Query("seed_genres") String seedGenres,
                                               @Query("seed_tracks") String seedTracks,
                                               @Query("min_acousticness") Float minAcousticness,
                                               @Query("max_acousticness") Float maxAcousticness,
                                               @Query("target_acousticness") Float targetAcousticness,
                                               @Query("min_danceability") Float minDanceability,
                                               @Query("max_danceability") Float maxDanceability,
                                               @Query("target_danceability") Float targetDanceability,
                                               @Query("min_duration_ms") Integer minDurationMs,
                                               @Query("max_duration_ms") Integer maxDurationMs,
                                               @Query("target_duration_ms") Integer targetDurationMs,
                                               @Query("min_energy") Float minEnergy,
                                               @Query("max_energy") Float maxEnergy,
                                               @Query("target_energy") Float targetEnergy,
                                               @Query("min_instrumentalness") Float minInstrumentalness,
                                               @Query("max_instrumentalness") Float maxInstrumentalness,
                                               @Query("target_instrumentalness") Float targetInstrumentalness,
                                               @Query("min_key") Integer minKey,
                                               @Query("max_key") Integer maxKey,
                                               @Query("target_key") Integer targetKey,
                                               @Query("min_liveness") Float minLiveness,
                                               @Query("max_liveness") Float maxLiveness,
                                               @Query("target_liveness") Float targetLiveness,
                                               @Query("min_loudness") Float minLoudness,
                                               @Query("max_loudness") Float maxLoudness,
                                               @Query("target_loudness") Float targetLoudness,
                                               @Query("min_mode") Integer minMode,
                                               @Query("max_mode") Integer maxMode,
                                               @Query("target_mode") Integer targetMode,
                                               @Query("min_popularity") Integer minPopularity,
                                               @Query("max_popularity") Integer maxPopularity,
                                               @Query("target_popularity") Integer targetPopularity,
                                               @Query("min_speechiness") Float minSpeechiness,
                                               @Query("max_speechiness") Float maxSpeechiness,
                                               @Query("target_speechiness") Float targetSpeechiness,
                                               @Query("min_tempo") Float minTempo,
                                               @Query("max_tempo") Float maxTempo,
                                               @Query("target_tempo") Float targetTempo,
                                               @Query("min_time_signature") Integer minTimeSignature,
                                               @Query("max_time_signature") Integer maxTimeSignature,
                                               @Query("target_time_signature") Integer targetTimeSignature,
                                               @Query("min_valence") Float minValence,
                                               @Query("max_valence") Float maxValence,
                                               @Query("target_valence") Float targetValence);
}
