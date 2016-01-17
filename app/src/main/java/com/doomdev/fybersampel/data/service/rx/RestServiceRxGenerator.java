package com.doomdev.fybersampel.data.service.rx;


import android.util.Log;

import com.doomdev.fybersampel.data.net.RestApi;
import com.doomdev.fybersampel.data.util.Converter;
import com.doomdev.fybersampel.presentation.view.fragment.FyberConnectionFragment;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;
import java.io.Reader;

import retrofit.GsonConverterFactory;
import retrofit.Response;
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
//        httpClient.interceptors().add(new HeaderInterceptor());
        // add your other interceptors
//        httpClient.interceptors().add(logging);  // <-- this is the important line!
        httpClient.interceptors().add(new HeaderInterceptor());
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


    public static class HeaderInterceptor
            implements Interceptor {
        @Override
        public com.squareup.okhttp.Response intercept(Chain chain)
                throws IOException {
            Request request = chain.request();

            com.squareup.okhttp.Response response = chain.proceed(request);

            String body = response.body().string();//convert(response.body().charStream());
            Log.d("RestServiceRxGenerator", "body:   " + body);

            String signature = response.headers().get("X-Sponsorpay-Response-Signature");
            Log.d("RestServiceRxGenerator", " get signanure from RESPONSE: " + signature);//&
            StringBuilder sb = new StringBuilder(body).append(FyberConnectionFragment.API_KEY);
            String signatureTocheck = Converter.sha1Hash(sb.toString());
            Log.d("RestServiceRxGenerator", " get signatureTocheck : " + signatureTocheck);
            return response.newBuilder().addHeader(RestApi.RESPONSE_HEADER_PARAM_ERROR_MESSAGE,body)
                    .body(ResponseBody.create(response.body().contentType(), body))
                    .build();
        }
    }
}
