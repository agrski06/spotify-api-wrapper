package api;

import retrofit2.http.GET;
import retrofit2.http.Url;

public interface PageService {
    @GET()
    <T extends BaseItem> SyncCall<Page<T>> nextPage(@Url String url);
}
