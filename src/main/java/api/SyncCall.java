package api;

import retrofit2.Call;
import retrofit2.HttpException;
import retrofit2.Response;

import java.io.IOException;

public class SyncCall<R> {
    Call<R> call;

    public SyncCall(Call<R> call) {
        this.call = call;
    }

    /**
     * Synchronous Call handler
     */
    public void handle(ResponseHandler<R> responseHandler) {
        try {
            Response<R> response = call.execute();
            handleResponse(response, responseHandler);
        } catch (IOException e) {
            responseHandler.accept(null, e);
        }
    }

    public R responseBody() {
        try {
            Response<R> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                if (response.code() == 401) {
                    // TODO: in future, implement refresh token there
                    TokenManager.getInstance().refreshToken();
                    throw new RuntimeException(response.message() + " (your token may be expired or revoked)");
                }
                if (response.code() == 429) {
                    throw new RuntimeException(response.message() + " (Spotify App rate limits exceeded?)");
                }
                if (response.code() >= 400 && response.code() <= 511) {
                    System.out.println(response.errorBody().string());
                    throw new HttpException(response);
                } else {
                    return response.body();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void handleResponse(Response<R> response, ResponseHandler<R> responseHandler) {
        if (response != null && response.isSuccessful()) {
            responseHandler.accept(response.body(), null);
        } else {
            if (response != null && response.code() >= 400 && response.code() <= 511) {
                responseHandler.accept(null, new HttpException(response));
            } else {
                responseHandler.accept(response != null ? response.body() : null, null);
            }
        }
    }

    public interface ResponseHandler<R> {
        /**
         * If request was successful, throwable is null (and vice-versa)
         */
        void accept(R response, Throwable throwable);
    }
}
