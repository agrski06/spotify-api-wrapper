package api.albums;

import api.Copyright;
import api.ExternalIds;
import api.ExternalUrls;
import api.Restrictions;
import api.artists.Artist;
import api.Image;
import api.tracks.SimplifiedTrack;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Album {
    private String albumType;
    private int totalTracks;
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
    private Set<Copyright> copyrights;
    private ExternalIds externalIds;
    private Set<String> genres;
    private String label;
    private int popularity;
    private Set<Artist> artists;
    private Set<SimplifiedTrack> tracks;
}
