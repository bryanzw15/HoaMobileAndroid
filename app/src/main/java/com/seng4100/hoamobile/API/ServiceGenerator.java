package com.seng4100.hoamobile.API;

import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ServiceGenerator {
    public static final String API_BASE_URL = "http://45.55.241.20";

    private static OkHttpClient httpClient = new OkHttpClient();
    private static Retrofit.Builder builder = new Retrofit.Builder().
            baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.client(httpClient).build();
        return retrofit.create(serviceClass);
    }
}
