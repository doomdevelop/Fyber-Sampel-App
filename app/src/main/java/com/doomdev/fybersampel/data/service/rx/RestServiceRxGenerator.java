package com.doomdev.fybersampel.data.service.rx;


import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by and on 10.01.16.
 */
public class RestServiceRxGenerator {
    private static OkHttpClient createOkHttpClientLogin() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient httpClient = new OkHttpClient();
        // add your other interceptors
        httpClient.interceptors().add(logging);  // <-- this is the important line!
        return httpClient;
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClientLogin())
                .build();
        return retrofit.create(serviceClass);


    }

}
