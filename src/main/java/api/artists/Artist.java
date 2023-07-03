package api.artists;

import api.BaseItem;
import api.ExternalUrls;
import api.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Artist extends BaseItem {
    private ExternalUrls externalUrls;
    private Followers followers;
    private Set<String> genres;
    private String href;
    private String id;
    private Set<Image> images;
    private int popularity;
    private String type;
    private String uri;
}
