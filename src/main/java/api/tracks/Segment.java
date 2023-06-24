package api.tracks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Segment {
    private Float start;
    private Float duration;
    private Float confidence;
    private Float loudnessStart;
    private Float loudnessMax;
    private Float loudnessMaxTime;
    private Float loudnessEnd;
    private Set<Float> pitches;
    private Set<Float> timbre;
}
