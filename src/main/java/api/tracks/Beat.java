package api.tracks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Beat {
    private Float start;
    private Float duration;
    private Float confidence;
}
