package api.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthTokenResponse extends Token {
    public String scope;
    public String refreshToken;

    public AuthTokenResponse(String accessToken, String tokenType, Integer expiresIn, String scope, String refreshToken) {
        super(accessToken, tokenType, expiresIn);
        this.scope = scope;
        this.refreshToken = refreshToken;
    }
}
