package api.tracks;

import api.ExternalUrls;
import api.Restrictions;
import api.tracks.album.TrackAlbum;
import api.tracks.artist.TrackArtist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

// This is literally track without 2 fields (externalIds, popularity). Why does spotify do this?
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SimplifiedTrack {
    private TrackAlbum album;
    private Set<TrackArtist> artists;
    private Set<String> availableMarkets;
    private Integer discNumber;
    private Integer durationMs;
    private boolean explicit;
    private ExternalUrls externalUrls;
    private String href;
    private String id;
    private boolean isPlayable;
    private LinkedFrom linkedFrom;
    private Restrictions restrictions;
    private String name;
    private String previewUrl;
    private Integer trackNumber;
    private String type;
    private String uri;
    private boolean isLocal;
}
