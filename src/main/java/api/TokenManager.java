package api;

import api.auth.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class TokenManager {
    private static final TokenManager INSTANCE = new TokenManager();

    private AuthType authType;
    private AuthToken authToken;
    private Token token;
    private String authString;

    private CredentialsService credentialsService;

    private TokenManager() {
    }

    public static TokenManager getInstance() {
        return INSTANCE;
    }

    public void refreshToken(String refreshToken, String authorization) {
        AuthTokenResponse response = credentialsService.getRefreshToken("refresh_token", refreshToken, authorization).responseBody();
        this.authToken.setAccessToken(response.getAccessToken());
        this.authToken.getScopes().addAll(Arrays.stream(response.getScope().split(" "))
                .map(AuthScope::fromString)
                .collect(Collectors.toCollection(HashSet::new)));
        this.authToken.setExpiresIn(response.getExpiresIn());
    }

    public void refreshToken() {
        if (authType.equals(AuthType.CLIENT_CREDENTIALS)) {
            this.token = credentialsService.getRefreshToken(authString, "client_credentials").responseBody();
            return;
        }
        AuthTokenResponse response = credentialsService.getRefreshToken("refresh_token", authToken.getRefreshToken(), this.authString).responseBody();
        this.authToken.setAccessToken(response.getAccessToken());
        this.authToken.getScopes().addAll(Arrays.stream(response.getScope().split(" "))
                .map(AuthScope::fromString)
                .collect(Collectors.toCollection(HashSet::new)));
        this.authToken.setExpiresIn(response.getExpiresIn());
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public void setAuthType(AuthType authType) {
        this.authType = authType;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public AuthType getAuthType() {
        return authType;
    }

    public AuthToken getAuthToken() {
        return authToken;
    }

    public Token getToken() {
        return token;
    }

    public CredentialsService getCredentialsService() {
        return credentialsService;
    }

    public void setCredentialsService(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    public String getAuthString() {
        return authString;
    }

    public void setAuthString(String authString) {
        this.authString = authString;
    }
}
