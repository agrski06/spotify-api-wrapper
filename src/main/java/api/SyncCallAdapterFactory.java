package api;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SyncCallAdapterFactory extends CallAdapter.Factory {
    private SyncCallAdapterFactory() {

    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (returnType instanceof ParameterizedType parameterizedType) {
            Type enclosingType = parameterizedType.getRawType();
            if (enclosingType == SyncCall.class) {
                Type type = parameterizedType.getActualTypeArguments()[0];
                return new SyncCallAdapter<>(type);
            }
        }

        return null;
    }

    public static SyncCallAdapterFactory create() {
        return new SyncCallAdapterFactory();
    }
}
