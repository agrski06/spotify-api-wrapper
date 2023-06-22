public class MainWrapper {
    public static void main(String[] args) {
        String clientId = args[0];
        String clientSecret = args[1];

        Spotify spotify = new Spotify.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();

        System.out.println(spotify.getToken());
        System.out.println(spotify.getSpotifyApi().getGenres());
    }
}
