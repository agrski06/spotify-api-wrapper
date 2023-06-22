package api;

import lombok.AllArgsConstructor;
import retrofit2.Call;
import retrofit2.CallAdapter;

import java.lang.reflect.Type;

@AllArgsConstructor
public class SimpleCallAdapter<R> implements CallAdapter<R, Object> {
    private Type responseType;

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public Object adapt(Call<R> call) {
        return new SyncCall<>(call);
    }
}
