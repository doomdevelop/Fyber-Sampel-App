package com.doomdev.fybersampel.data.service.rx;


import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by and on 10.01.16.
 */
public class RestServiceRxGenerator {
        public static <S> S createService(Class<S> serviceClass, String baseUrl) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(serviceClass);


        }

}
