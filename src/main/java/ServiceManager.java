import api.albums.AlbumService;
import api.artists.ArtistService;
import api.genres.GenreService;
import api.markets.MarketsService;
import api.search.SearchService;
import api.tracks.TrackService;
import lombok.Getter;
import retrofit2.Retrofit;

@Getter
public class ServiceManager {
    private final Retrofit retrofit;

    private final ArtistService artistService;
    private final GenreService genreService;
    private final TrackService trackService;
    private final SearchService searchService;
    private final MarketsService marketsService;
    private final AlbumService albumService;

    public ServiceManager(Retrofit retrofit) {
        this.retrofit = retrofit;
        this.artistService = retrofit.create(ArtistService.class);
        this.genreService = retrofit.create(GenreService.class);
        this.trackService = retrofit.create(TrackService.class);
        this.searchService = retrofit.create(SearchService.class);
        this.marketsService = retrofit.create(MarketsService.class);
        this.albumService = retrofit.create(AlbumService.class);
    }
}
