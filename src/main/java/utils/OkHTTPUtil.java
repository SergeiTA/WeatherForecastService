package utils;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

public class OkHTTPUtil {


    private static final OkHttpClient httpClient = new OkHttpClient().newBuilder()
            .addInterceptor(new DefaultContentTypeInterceptor())
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .build();


    public static OkHttpClient getHttpClient() {
        return httpClient;
    }

}
