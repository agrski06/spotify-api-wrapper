package api.tracks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Recommendation {
    private Set<RecommendationSeed> seeds;
    private Set<Track> tracks;
}
