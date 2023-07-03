import api.PageService;
import api.SyncCall;
import api.SyncCallAdapterFactory;
import api.albums.*;
import api.artists.Artist;
import api.artists.ArtistPage;
import api.artists.ArtistsResponse;
import api.auth.AuthScope;
import api.auth.AuthToken;
import api.auth.AuthType;
import api.auth.Token;
import api.genres.Genre;
import api.search.SearchResponse;
import api.search.SearchType;
import api.tracks.*;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SpotifyApi {
    private final Retrofit retrofit;
    private final ServiceManager serviceManager;
    private AuthType authType;
    private Set<AuthScope> authScopes;
    private Set<String> defaultMarkets = null;

    public SpotifyApi(Token token, boolean enableLogging) {
        if (token instanceof AuthToken) {
            this.authType = AuthType.AUTH_CODE;
            this.authScopes = ((AuthToken) token).getScopes();
        } else {
            this.authType = AuthType.CLIENT_CREDENTIALS;
        }
        OkHttpClient.Builder clientWithAuthHeader = new OkHttpClient().newBuilder()
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    request = request.newBuilder()
                            .addHeader("Authorization", "Bearer " + token.getAccessToken())
                            .build();
                    return chain.proceed(request);
                });

        if (enableLogging) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            clientWithAuthHeader.addInterceptor(logging);
        }

        HttpUrl url = new HttpUrl.Builder()
                .scheme("https")
                .host("api.spotify.com")
                .addPathSegment("v1")
                .addPathSegment("") // creates trailing slash
                .build();

        Gson gson = new Gson().newBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();

        this.retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(SyncCallAdapterFactory.create())
                .client(clientWithAuthHeader.build())
                .build();

        this.serviceManager = new ServiceManager(retrofit);
    }

    private void checkAuth(AuthScope scope, String errorMessage) {
        if (authType != AuthType.AUTH_CODE) {
            throw new RuntimeException("Cannot read user's tracks (invalid auth type).");
        }
        if (!authScopes.contains(scope)) {
            throw new RuntimeException(errorMessage);
        }
    }

    private String defaultMarketString() {
        return defaultMarkets == null ? null : String.join(",", defaultMarkets);
    }

    private void checkAuth(Set<AuthScope> scopes, String errorMessage) {
        if (authType != AuthType.AUTH_CODE) {
            throw new RuntimeException("Cannot read user's tracks (invalid auth type).");
        }
        if (!authScopes.containsAll(scopes)) {
            throw new RuntimeException(errorMessage);
        }
    }

    public void setDefaultMarkets(Set<String> defaultMarkets) {
        this.defaultMarkets = defaultMarkets;
    }

    //////////////////////////////////////
    //           API METHODS
    //////////////////////////////////////

    public ArtistPage getNextArtistsPage(ArtistPage page) {
        PageService pageService = retrofit.create(PageService.class);
        System.out.println(retrofit.baseUrl());
        try {
            URL url = new URL(page.getNext());
            return pageService.getArtistPage("search?" + url.getQuery()).responseBody().getArtists();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //////////////////////////////////////
    //           ALBUMS
    //////////////////////////////////////

    public Album getAlbum(String id) {
        return serviceManager.getAlbumService().getAlbum(id, defaultMarkets.size() > 0
                ? String.join(",", defaultMarkets)
                : null).responseBody();
    }

    public Album getAlbum(String id, String market) {
        return serviceManager.getAlbumService().getAlbum(id, market).responseBody();
    }

    public Set<Album> getAlbums(String ids) {
        return serviceManager.getAlbumService().getAlbums(ids, defaultMarkets.size() > 0
                ? String.join(",", defaultMarkets)
                : null).responseBody();
    }

    public Set<Album> getAlbums(String ids, String market) {
        return serviceManager.getAlbumService().getAlbums(ids, market).responseBody();
    }

    public Set<Album> getAlbums(String... ids) {
        return serviceManager.getAlbumService().getAlbums(String.join(",", ids), defaultMarkets.size() > 0
                ? String.join(",", defaultMarkets)
                : null).responseBody();
    }

    public Set<Album> getAlbums(String market, String... ids) {
        return serviceManager.getAlbumService().getAlbums(String.join(",", ids), market).responseBody();
    }

    public TrackPage getAlbumTracks(String id, String market, Integer limit, Integer offset) {
        return serviceManager.getAlbumService().getAlbumTracks(id, market, limit, offset).responseBody();
    }

    public TrackPage getAlbumTracks(String id) {
        return getAlbumTracks(id, null, null, null);
    }

    public AlbumPage getUsersAlbums(String market, Integer limit, Integer offset) {
        checkAuth(AuthScope.USER_LIBRARY_READ, "Cannot read user's albums (no user-library-read scope).");
        return serviceManager.getAlbumService().getUsersAlbums(limit, offset, market).responseBody();
    }

    public AlbumPage getUsersAlbums() {
        checkAuth(AuthScope.USER_LIBRARY_READ, "Cannot read user's albums (no user-library-read scope).");
        return serviceManager.getAlbumService().getUsersAlbums(null, null, null).responseBody();
    }

    public boolean saveAlbumsForUser(SaveAlbumRequest request) {
        checkAuth(AuthScope.USER_LIBRARY_MODIFY, "Cannot save album (no user-library-modify scope).");
        return serviceManager.getAlbumService().saveAlbumsForUser(request.getIds().toString(), request).execute().isSuccessful();
    }

    public boolean deleteAlbumsForUser(RemoveAlbumRequest request) {
        checkAuth(AuthScope.USER_LIBRARY_MODIFY, "Cannot delete album (no user-library-modify scope).");
        return serviceManager.getAlbumService().deleteAlbumsFromUser(request.getIds().toString(), request).execute().isSuccessful();
    }

    public Set<Boolean> checkIfUserHasAlbumsSaved(String... ids) {
        checkAuth(AuthScope.USER_LIBRARY_READ, "Cannot read user's albums (no user-library-read scope).");
        return serviceManager.getAlbumService().checkIfUserHasAlbumsSaved(String.join(",", ids)).responseBody();
    }

    public NewReleasesResponse getNewReleases(String country, Integer limit, Integer offset) {
        return serviceManager.getAlbumService().getNewReleases(country, limit, offset).responseBody();
    }

    public NewReleasesResponse getNewReleases() {
        return serviceManager.getAlbumService().getNewReleases(null, null, null).responseBody();
    }

    //////////////////////////////////////
    //           ARTISTS
    //////////////////////////////////////

    public Artist getArtist(String id) {
        return serviceManager.getArtistService().getArtist(id).responseBody();
    }

    public Set<Artist> getArtist(String... ids) {
        return serviceManager.getArtistService().getArtists(String.join(",", ids)).responseBody().getArtists();
    }

    public AlbumPage getArtistAlbum(String id, String includeGroups, String market, Integer limit, Integer offset) {
        return serviceManager.getArtistService().getArtistAlbums(id, includeGroups, market, limit, offset).responseBody();
    }

    public AlbumPage getArtistAlbum(String id) {
        return getArtistAlbum(id, null, null, null, null);
    }

    public Set<Track> getArtistTopTracks(String id, String market) {
        return serviceManager.getArtistService().getArtistTopTracks(id, market).responseBody().getTracks();
    }

    public Set<Artist> getRelatedArtists(String id) {
        return serviceManager.getArtistService().getRelatedArtists(id).responseBody().getArtists();
    }

    //////////////////////////////////////
    //           GENRES
    //////////////////////////////////////

    public Set<Genre> getGenres() {
        return serviceManager.getGenreService().getGenres().responseBody()
                .getGenres()
                .stream().map(Genre::new)
                .collect(Collectors.toSet());
    }

    //////////////////////////////////////
    //           MARKETS
    //////////////////////////////////////

    public Set<String> getMarkets() {
        return serviceManager.getMarketsService().getMarkets().responseBody().getMarkets();
    }

    //////////////////////////////////////
    //           SEARCH
    //////////////////////////////////////

    public SearchResponse search(String query, SearchType type, String market, Integer limit, Integer offset, String includeExternal) {
        return serviceManager.getSearchService().search(query, type == null ? SearchType.getAllAsCommaSeparatedString() : type.toString(), market, limit, offset, includeExternal).responseBody();
    }

    public SearchResponse search(String query) {
        return search(query, null, defaultMarketString(), null, null, null);
    }

    public SearchResponse search(String query, SearchType type) {
        return search(query, type, defaultMarketString(), null, null, null);
    }

    public AlbumPage searchAlbum(String query) {
        return search(query, SearchType.ALBUM, defaultMarketString(), null, null, null).getAlbums();
    }

    public TrackPage searchTrack(String query) {
        return search(query, SearchType.TRACK, defaultMarketString(), null, null, null).getTracks();
    }

    public ArtistPage searchArtist(String query) {
        return search(query, SearchType.ARTIST, defaultMarketString(), null, null, null).getArtists();
    }

    // TODO: more search options

    //////////////////////////////////////
    //           TRACKS
    //////////////////////////////////////

    public Track getTrackById(String id) {
        return serviceManager.getTrackService().getTrack(id).responseBody();
    }

    public Track getTrackById(String id, String market) {
        return serviceManager.getTrackService().getTrack(id, market).responseBody();
    }

    public Set<Track> getTracks(List<String> ids, String market) {
        String idsString = String.join(",", ids);
        return serviceManager.getTrackService().getTracks(idsString, market).responseBody().getTracks();
    }

    public Set<Track> getTracks(List<String> ids) {
        String idsString = String.join(",", ids);
        return serviceManager.getTrackService().getTracks(idsString).responseBody().getTracks();
    }

    public Set<Track> getTracks(String market, String... ids) {
        String idsString = String.join(",", ids);
        return serviceManager.getTrackService().getTracks(idsString, market).responseBody().getTracks();
    }

    public Set<Track> getTracks(String... ids) {
        String idsString = String.join(",", ids);
        return serviceManager.getTrackService().getTracks(idsString).responseBody().getTracks();
    }

    public TrackPage getUsersTracks(String market, Integer limit, Integer offset) {
        checkAuth(AuthScope.USER_LIBRARY_READ, "Cannot read user's tracks (no user-library-read scope).");
        return serviceManager.getTrackService().getSavedTracks(market, limit, offset).responseBody();
    }

    public void saveTracksForUser(TracksRequest request) {
        checkAuth(AuthScope.USER_LIBRARY_MODIFY, "Cannot save user's tracks (no user-library-modify scope).");
        serviceManager.getTrackService().saveTracksForCurrentUser(request).handle((response, throwable) -> {
            if (throwable != null) {
                throw new RuntimeException(throwable.getMessage());
            }
        });
    }

    /**
     * Removes track from user's library
     * @param request object containing set of ids
     */
    public void removeTracksForUser(TracksRequest request) {
        checkAuth(AuthScope.USER_LIBRARY_MODIFY, "Cannot delete user's tracks (no user-library-modify scope).");
        serviceManager.getTrackService().deleteTracksForCurrentUser(request.toCommaSeparatedString(), request).handle((response, throwable) -> {
            if (throwable != null) {
                throw new RuntimeException(throwable.getMessage());
            }
        });
    }

    /**
     * Check whether the user has specified tracks saved in library
     * @param ids comma-separated list of the Spotify IDs
     * @return set of booleans
     */
    public Set<Boolean> checkIfUserHasTrackSaved(String ids) {
        checkAuth(AuthScope.USER_LIBRARY_READ, "Cannot read user's tracks (no user-library-read scope).");
        return serviceManager.getTrackService().checkTracksForCurrentUser(ids).responseBody();
    }

    /**
     * Check whether the user has specified tracks saved in library
     * @param ids list of ids
     * @return set of booleans
     */
    public Set<Boolean> checkIfUserHasTrackSaved(String... ids) {
        checkAuth(AuthScope.USER_LIBRARY_READ, "Cannot read user's tracks (no user-library-read scope).");
        return serviceManager.getTrackService().checkTracksForCurrentUser(String.join(",", ids)).responseBody();
    }

    public Set<AudioFeatures> getAudioFeatures(List<String> ids) {
        String idsString = String.join(",", ids);
        return serviceManager.getTrackService().getAudioFeaturesForTracks(idsString).responseBody().getAudioFeatures();
    }

    public Set<AudioFeatures> getAudioFeatures(String... ids) {
        String idsString = String.join(",", ids);
        return serviceManager.getTrackService().getAudioFeaturesForTracks(idsString).responseBody().getAudioFeatures();
    }

    public AudioFeatures getAudioFeatures(String id) {
        return serviceManager.getTrackService().getAudioFeaturesForTrack(id).responseBody();
    }

    public AudioFeatures getAudioFeatures(Track track) {
        return serviceManager.getTrackService().getAudioFeaturesForTrack(track.getId()).responseBody();
    }

    public AudioAnalysis getAudioAnalysis(String id) {
        return serviceManager.getTrackService().getAudioAnalysis(id).responseBody();
    }

    public Recommendation getRecommendation(RecommendationRequest request) {
        if (defaultMarkets != null && request.getMarket() == null) {
            request.setMarket(String.join(",", defaultMarkets));
        }
        return serviceManager.getTrackService().getRecommendation(
                request.getLimit(),
                request.getMarket(),
                request.getSeedArtists(),
                request.getSeedGenres(),
                request.getSeedTracks(),
                request.getMinAcousticness(),
                request.getMaxAcousticness(),
                request.getTargetAcousticness(),
                request.getMinDanceability(),
                request.getMaxDanceability(),
                request.getTargetDanceability(),
                request.getMinDurationMs(),
                request.getMaxDurationMs(),
                request.getTargetDurationMs(),
                request.getMinEnergy(),
                request.getMaxEnergy(),
                request.getTargetEnergy(),
                request.getMinInstrumentalness(),
                request.getMaxInstrumentalness(),
                request.getTargetInstrumentalness(),
                request.getMinKey(),
                request.getMaxKey(),
                request.getTargetKey(),
                request.getMinLiveness(),
                request.getMaxLiveness(),
                request.getTargetLiveness(),
                request.getMinLoudness(),
                request.getMaxLoudness(),
                request.getTargetLoudness(),
                request.getMinMode(),
                request.getMaxMode(),
                request.getTargetMode(),
                request.getMinPopularity(),
                request.getMaxPopularity(),
                request.getTargetPopularity(),
                request.getMinSpeechiness(),
                request.getMaxSpeechiness(),
                request.getTargetSpeechiness(),
                request.getMinTempo(),
                request.getMaxTempo(),
                request.getTargetTempo(),
                request.getMinTimeSignature(),
                request.getMaxTimeSignature(),
                request.getTargetTimeSignature(),
                request.getMinValence(),
                request.getMaxValence(),
                request.getTargetValence()
        ).responseBody();
    }

}
