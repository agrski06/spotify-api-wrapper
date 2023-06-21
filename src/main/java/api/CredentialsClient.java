package requester;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CredentialsClient {
    @POST
    Call<Token> getToken(@Body Credentials credentials);
}
