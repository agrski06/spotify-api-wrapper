package api.auth;

import api.SyncCall;
import retrofit2.Call;
import retrofit2.http.*;

public interface CredentialsService {
    @POST("api/token")
    @FormUrlEncoded
    SyncCall<AuthTokenResponse> getRefreshToken(@Field("grant_type") String grantType,
                                                @Field("refresh_token") String refreshToken,
                                                @Header("Authorization") String authorization);

    @POST("api/token")
    @FormUrlEncoded
    SyncCall<Token> getToken(@Field("client_id") String clientId,
                             @Field("client_secret") String clientSecret,
                             @Field("grant_type") String grantType);

    @POST("api/token")
    @FormUrlEncoded
    SyncCall<Token> getRefreshToken(@Header("Authorization") String authHeader,
                                    @Field("grant_type") String request);

    @GET("authorize")
    Call<Token> getToken(@Query("client_id") String clientId,
                             @Query("response_type") String responseType,
                             @Query("redirect_uri") String redirectUri,
                             @Query("state") String state,
                             @Query("scope") String scope,
                             @Query("show_dialog") boolean showDialog);

    @POST("api/token")
    @FormUrlEncoded
    SyncCall<AuthTokenResponse> getAuthToken(@Header("Authorization") String authorization,
                                             @Field("grant_type") String grantType,
                                             @Field("code") String code,
                                             @Field("redirect_uri") String redirectUri);
}
