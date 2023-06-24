package api.tracks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AudioAnalysis {
    private Meta meta;
    private AnalyzedTrack track;
    private Set<Bar> bars;
    private Set<Beat> beats;
    private Set<Section> sections;
    private Set<Segment> segments;
    private Set<Tatum> tatums;
}
