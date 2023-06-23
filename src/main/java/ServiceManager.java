import api.genres.GenreService;
import api.tracks.TrackService;
import lombok.Getter;
import retrofit2.Retrofit;

@Getter
public class ServiceManager {
    private final Retrofit retrofit;

    private final GenreService genreService;
    private final TrackService trackService;

    public ServiceManager(Retrofit retrofit) {
        this.retrofit = retrofit;
        this.genreService = retrofit.create(GenreService.class);
        this.trackService = retrofit.create(TrackService.class);
    }
}
