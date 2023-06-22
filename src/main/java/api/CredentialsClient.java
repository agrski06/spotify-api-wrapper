package api;

import api.auth.Credentials;
import api.auth.Token;
import retrofit2.Call;
import retrofit2.http.*;

public interface CredentialsClient {
    @POST("api/token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<Token> getToken(@Body Credentials credentials);

    @POST("api/token")
    @FormUrlEncoded
    Call<Token> getToken(@Field("client_id") String clientId,
                         @Field("client_secret") String clientSecret,
                         @Field("grant_type") String grantType);
}
