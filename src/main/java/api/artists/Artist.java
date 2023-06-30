package api.artists;

import api.ExternalUrls;
import api.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Artist {
    private ExternalUrls externalUrls;
    private Followers followers;
    private Set<String> genres;
    private String href;
    private String id;
    private Set<Image> images;
    private String name;
    private int popularity;
    private String type;
    private String uri;
}
