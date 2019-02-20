package com.rhino.libretrofit2okhttp3;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author LuoLin
 * @since Create on 2019/2/19.
 */
public class HttpUtils {

    public static int TIMEOUT_SECONDS_CONNECT = 60;
    public static int TIMEOUT_SECONDS_WRITE = 60;
    public static int TIMEOUT_SECONDS_READ = 60;
    public static String BASE_URL = null;
    public Retrofit mRetrofit;
    public static HttpUtils mInstance = new HttpUtils();

    public static HttpUtils instance() {
        return mInstance;
    }

    protected HttpUtils() {
    }

    protected OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_SECONDS_CONNECT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_SECONDS_WRITE, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_SECONDS_READ, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    protected Retrofit getRetrofit() {
        if (BASE_URL == null) {
            throw new RuntimeException("BASE_URL is null!");
        }
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getOkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T getService(Class<T> cls) {
        if (mRetrofit == null) {
            mRetrofit = getRetrofit();
        }
        return mRetrofit.create(cls);
    }


}
