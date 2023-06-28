package api.auth;

import lombok.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class AuthToken extends Token{
    public Set<AuthScope> scopes = new HashSet<>();
    public String refreshToken;

    public AuthToken(AuthTokenResponse response) {
        super(response.getAccessToken(), response.getTokenType(), response.getExpiresIn());
        this.scopes = Arrays.stream(response.getScope().split(" "))
                .map(AuthScope::fromString)
                .collect(Collectors.toCollection(HashSet::new));
        this.refreshToken = response.getRefreshToken();
    }
}
