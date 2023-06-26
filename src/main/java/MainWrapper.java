import api.tracks.RecommendationRequest;

public class MainWrapper {
    public static void main(String[] args) {
        String clientId = args[0];
        String clientSecret = args[1];

        Spotify spotify = new Spotify.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setCode("AQBJDXwP-A2yoz3jOUH1VKULKCP771y6xK8MUmLAg_8yAXCc_D-mILD4V2SH9AwYO_YqlQ6VnNAtYCI-UNZBIuqnNWfT0YeAd3eP_m_P68X2ZzyT8a7qiVkrXgJ-GcaJFmAHy-IfMNj3MtEbsPi_Lt-leUTb0wcosi9xUXH-1jC7eg")
                .setRedirectUri("http://localhost:5173/callback")
                .build();


        System.out.println(spotify.generateAuthUri(clientId, "http://localhost:5173/callback", null, null, false));

        SpotifyApi spotifyApi = spotify.getSpotifyApi();
        RecommendationRequest request = RecommendationRequest.builder()
                .seedArtists("4NHQUGzhtTLFvgF5SZesLK")
                .seedGenres("classical")
                .seedTracks("0c6xIDDpzE81m2q797ordA")
                .build();
        System.out.println(spotifyApi.getRecommendation(request));
    }
}
