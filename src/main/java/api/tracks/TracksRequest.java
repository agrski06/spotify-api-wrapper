package api.tracks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TracksRequest {
    private Set<String> ids;

    public String toCommaSeparatedString() {
        return String.join(",", ids);
    }
}
