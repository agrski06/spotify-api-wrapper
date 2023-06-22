package api;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SimpleCallAdapterFactory extends CallAdapter.Factory {
    private SimpleCallAdapterFactory() {

    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (returnType instanceof ParameterizedType parameterizedType) {
            Type enclosingType = parameterizedType.getRawType();
            if (enclosingType == SyncCall.class) {
                Type type = parameterizedType.getActualTypeArguments()[0];
                return new SimpleCallAdapter<>(type);
            }
        }

        return null;
    }

    public static SimpleCallAdapterFactory create() {
        return new SimpleCallAdapterFactory();
    }
}
