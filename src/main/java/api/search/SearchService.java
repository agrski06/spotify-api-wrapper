package api.search;

import api.SyncCall;
import retrofit2.http.Query;

public interface SearchService {
    SyncCall<SearchResponse> search(@Query("q") String query,
                                    @Query("type") String type,
                                    @Query("market") String market,
                                    @Query("limit") Integer limit,
                                    @Query("offset") Integer offset,
                                    @Query("include_external") String includeExternal);
}
