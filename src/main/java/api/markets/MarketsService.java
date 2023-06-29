package api.markets;

import api.SyncCall;
import retrofit2.http.GET;

import java.util.Set;

public interface MarketsService {
    @GET("markets")
    SyncCall<MarketsResponse> getMarkets();
}
