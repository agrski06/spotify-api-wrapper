package api.tracks.artist;

import api.tracks.ExternalUrls;
import api.tracks.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrackArtist {
    private ExternalUrls externalUrls;
    private Followers followers;
    private Set<String> genres;
    private String href;
    private String id;
    private Set<Image> images;
    private String name;
    private Integer popularity;
    private String type;
    private String uri;
}
