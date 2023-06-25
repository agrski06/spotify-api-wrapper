package api.tracks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecommendationSeed {
    private Integer afterFilteringSize;
    private Integer afterRelinkingSize;
    private String href;
    private String id;
    private Integer initialPoolSize;
    private String type;
}
