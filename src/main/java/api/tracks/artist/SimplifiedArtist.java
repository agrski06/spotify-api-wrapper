package api.tracks.artist;

import api.tracks.ExternalUrls;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimplifiedArtist {
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private String name;
    private String type;
    private String uri;
}
