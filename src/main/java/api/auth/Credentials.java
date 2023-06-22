package api.auth;

import lombok.Data;

@Data
public class Credentials {
    private String clientId;
    private String clientSecret;
    private String grantType;

    public Credentials() {
        this.grantType = "client_credentials";
    }

    public Credentials(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.grantType = "client_credentials";
    }
}
