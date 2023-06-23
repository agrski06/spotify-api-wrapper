package api.tracks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AudioFeatures {
    private Float acousticness;
    private String analysisUrl;
    private Float danceability;
    private Integer durationMs;
    private Float energy;
    private String id;
    private Float instrumentalness;
    private Integer key;
    private Float liveness;
    private Float loudness;
    private Integer mode;
    private Float speechiness;
    private Float tempo;
    private Integer timeSignature;
    private String trackHref;
    private String type;
    private String uri;
    private Float valance;
}
