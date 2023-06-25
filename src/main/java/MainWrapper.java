import api.tracks.RecommendationRequest;

public class MainWrapper {
    public static void main(String[] args) {
        String clientId = args[0];
        String clientSecret = args[1];

        Spotify spotify = new Spotify.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .enableHttpRequestLogging()
                .build();

        SpotifyApi spotifyApi = spotify.getSpotifyApi();
        RecommendationRequest request = RecommendationRequest.builder()
                .seedArtists("4NHQUGzhtTLFvgF5SZesLK")
                .seedGenres("classical")
                .seedTracks("0c6xIDDpzE81m2q797ordA")
                .build();
        System.out.println(spotifyApi.getRecommendation(request));

    }
}
