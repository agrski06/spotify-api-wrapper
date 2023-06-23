import java.util.List;

public class MainWrapper {
    public static void main(String[] args) {
        String clientId = args[0];
        String clientSecret = args[1];

        Spotify spotify = new Spotify.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();

        SpotifyApi spotifyApi = spotify.getSpotifyApi();
        System.out.println(spotifyApi.getAudioFeatures(List.of("7ouMYWpwJ422jRcDASZB7P")));

    }
}
