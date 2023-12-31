package api.tracks.album;

import api.Copyright;
import api.ExternalIds;
import api.ExternalUrls;
import api.Image;
import api.Restrictions;
import api.artists.SimplifiedArtist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TrackAlbum {
    private String albumType; //todo enum
    private Integer totalTracks;
    private Set<String> availableMarkets;
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private Set<Image> images;
    private String name;
    private String releaseDate;
    private String releaseDatePrecision;
    private Restrictions restrictions;
    private String type;
    private String uri;
    private Copyright copyrights;
    private ExternalIds externalIds;
    private Set<String> genres;
    private String label;
    private Integer popularity;
    private String albumGroup;
    private Set<SimplifiedArtist> artists;
}
