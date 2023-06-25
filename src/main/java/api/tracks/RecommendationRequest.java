package api.tracks;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecommendationRequest {
    private Integer limit;
    private String market;
    private String seedArtists;
    private String seedGenres;
    private String seedTracks;
    private Float minAcousticness;
    private Float maxAcousticness;
    private Float targetAcousticness;
    private Float minDanceability;
    private Float maxDanceability;
    private Float targetDanceability;
    private Integer minDurationMs;
    private Integer maxDurationMs;
    private Integer targetDurationMs;
    private Float minEnergy;
    private Float maxEnergy;
    private Float targetEnergy;
    private Float minInstrumentalness;
    private Float maxInstrumentalness;
    private Float targetInstrumentalness;
    private Integer minKey;
    private Integer maxKey;
    private Integer targetKey;
    private Float minLiveness;
    private Float maxLiveness;
    private Float targetLiveness;
    private Float minLoudness;
    private Float maxLoudness;
    private Float targetLoudness;
    private Integer minMode;
    private Integer maxMode;
    private Integer targetMode;
    private Integer minPopularity;
    private Integer maxPopularity;
    private Integer targetPopularity;
    private Float minSpeechiness;
    private Float maxSpeechiness;
    private Float targetSpeechiness;
    private Float minTempo;
    private Float maxTempo;
    private Float targetTempo;
    private Integer minTimeSignature;
    private Integer maxTimeSignature;
    private Integer targetTimeSignature;
    private Float minValence;
    private Float maxValence;
    private Float targetValence;

    public Map<String, String> getQueryString() {
        HashMap<String, String> queryMap = new HashMap<>();
        if (this.limit != null) queryMap.put("limit", this.limit.toString());
        if (this.market != null) queryMap.put("market", this.market);
        if (this.seedArtists != null) queryMap.put("seed_artists", this.seedArtists);
        if (this.seedGenres != null) queryMap.put("seed_genres", this.seedGenres);
        if (this.seedTracks != null) queryMap.put("seed_tracks", this.seedTracks);
        if (this.minAcousticness != null) queryMap.put("min_acousticness", this.minAcousticness.toString());
        if (this.maxAcousticness != null) queryMap.put("max_acousticness", this.maxAcousticness.toString());
        if (this.targetAcousticness != null) queryMap.put("target_acousticness", this.targetAcousticness.toString());
        if (this.minDanceability != null) queryMap.put("min_danceability", this.minDanceability.toString());
        if (this.maxDanceability != null) queryMap.put("max_danceability", this.maxDanceability.toString());
        if (this.targetDanceability != null) queryMap.put("target_danceability", this.targetDanceability.toString());
        if (this.minDurationMs != null) queryMap.put("min_duration_ms", this.minDurationMs.toString());
        if (this.maxDurationMs != null) queryMap.put("max_duration_ms", this.maxDurationMs.toString());
        if (this.targetDurationMs != null) queryMap.put("target_duration_ms", this.targetDurationMs.toString());
        if (this.minEnergy != null) queryMap.put("min_energy", this.minEnergy.toString());
        if (this.maxEnergy != null) queryMap.put("max_energy", this.maxEnergy.toString());
        if (this.targetEnergy != null) queryMap.put("target_energy", this.targetEnergy.toString());
        if (this.minInstrumentalness != null) queryMap.put("min_instrumentalness", this.minInstrumentalness.toString());
        if (this.maxInstrumentalness != null) queryMap.put("max_instrumentalness", this.maxInstrumentalness.toString());
        if (this.targetInstrumentalness != null) queryMap.put("target_instrumentalness", this.targetInstrumentalness.toString());
        if (this.minKey != null) queryMap.put("min_key", this.minKey.toString());
        if (this.maxKey != null) queryMap.put("max_key", this.maxKey.toString());
        if (this.targetKey != null) queryMap.put("target_key", this.targetKey.toString());
        if (this.minLiveness != null) queryMap.put("min_liveness", this.minLiveness.toString());
        if (this.maxLiveness != null) queryMap.put("max_liveness", this.maxLiveness.toString());
        if (this.targetLiveness != null) queryMap.put("target_liveness", this.targetLiveness.toString());
        if (this.minLoudness != null) queryMap.put("min_loudness", this.minLoudness.toString());
        if (this.maxLoudness != null) queryMap.put("max_loudness", this.maxLoudness.toString());
        if (this.targetLoudness != null) queryMap.put("target_loudness", this.targetLoudness.toString());
        if (this.minMode != null) queryMap.put("min_mode", this.minMode.toString());
        if (this.maxMode != null) queryMap.put("max_mode", this.maxMode.toString());
        if (this.targetMode != null) queryMap.put("target_mode", this.targetMode.toString());
        if (this.minPopularity != null) queryMap.put("min_popularity", this.minPopularity.toString());
        if (this.maxPopularity != null) queryMap.put("max_popularity", this.maxPopularity.toString());
        if (this.targetPopularity != null) queryMap.put("target_popularity", this.targetPopularity.toString());
        if (this.minSpeechiness != null) queryMap.put("min_speechiness", this.minSpeechiness.toString());
        if (this.maxSpeechiness != null) queryMap.put("max_speechiness", this.maxSpeechiness.toString());
        if (this.targetSpeechiness != null) queryMap.put("target_speechiness", this.targetSpeechiness.toString());
        if (this.minTempo != null) queryMap.put("min_tempo", this.minTempo.toString());
        if (this.maxTempo != null) queryMap.put("max_tempo", this.maxTempo.toString());
        if (this.targetTempo != null) queryMap.put("target_tempo", this.targetTempo.toString());
        if (this.minTimeSignature != null) queryMap.put("min_time_signature", this.minTimeSignature.toString());
        if (this.maxTimeSignature != null) queryMap.put("max_time_signature", this.maxTimeSignature.toString());
        if (this.targetTimeSignature != null) queryMap.put("target_time_signature", this.targetTimeSignature.toString());
        if (this.minValence != null) queryMap.put("min_valence", this.minValence.toString());
        if (this.maxValence != null) queryMap.put("max_valence", this.maxValence.toString());
        if (this.targetValence != null) queryMap.put("target_valence", this.targetValence.toString());

        return queryMap;
    }

}
