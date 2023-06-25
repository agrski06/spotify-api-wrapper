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
}
