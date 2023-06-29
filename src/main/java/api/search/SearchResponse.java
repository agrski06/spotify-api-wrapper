package api.search;

import api.artists.ArtistPage;
import api.tracks.TrackPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SearchResponse {
    private TrackPage tracks;
    private ArtistPage artists;
    // TODO: albums, playlists, shows, episodes, audiobooks
}
