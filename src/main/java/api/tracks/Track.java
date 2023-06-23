package api.tracks;

import api.tracks.album.TrackAlbum;
import api.tracks.artist.TrackArtist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Track {
    private TrackAlbum album;
    private Set<TrackArtist> artists;
    private Set<String> availableMarkets;
    private Integer discNumber;
    private Integer durationMs;
    private boolean explicit;
    private ExternalIds externalIds;
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private boolean isPlayable;
    private LinkedFrom linkedFrom;
    private Restrictions restrictions;
    private String name;
    private Integer popularity;
    private String previewUrl;
    private Integer trackNumber;
    private String type;
    private String uri;
    private boolean isLocal;
}
