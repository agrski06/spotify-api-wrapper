package api.auth;

public class RefreshTokenClientCredentialsRequest {
    String grantType;

    public RefreshTokenClientCredentialsRequest() {
        this.grantType = "client_credentials";
    }
}
