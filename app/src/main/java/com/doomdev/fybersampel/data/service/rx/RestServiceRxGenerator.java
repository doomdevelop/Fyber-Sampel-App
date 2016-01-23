package com.doomdev.fybersampel.data.service.rx;


import com.doomdev.fybersampel.data.exception.WrongSignatureException;
import com.doomdev.fybersampel.data.util.Const;
import com.doomdev.fybersampel.data.util.Converter;
import com.doomdev.fybersampel.presenter.util.FyberParameterHelper;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.ResponseBody;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.io.IOException;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * this class contain static methods to generate retrofit api service
 * Created by and on 10.01.16.
 */
public class RestServiceRxGenerator {
    /**
     * Create HttpClient with Interceptors
     *
     * @return {@link OkHttpClient} with custom {@link HeaderInterceptor}
     */
    private static OkHttpClient createOkHttpClientLogin() {
        OkHttpClient httpClient = new OkHttpClient();
        // to print http logs
        // httpClient.interceptors().add(getLoggingInterceptor);
        httpClient.interceptors().add(new HeaderInterceptor());
        return httpClient;
    }

    /**
     * Create retrofit service for api call defined by the giving interface tpe
     * @param serviceClass the service interface containing api method
     * @param baseUrl the url mast be call
     * @param <S> api interface type
     * @return implementation of the api defined by the service interface
     */
    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClientLogin())
                .build();
        return retrofit.create(serviceClass);
    }

    private static HttpLoggingInterceptor getLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logging;
    }

    /**
     * The custom header interceptor validate signature from response header.
     * In case signature is not valid the {@link WrongSignatureException} will be thrown
     * If response body contain code != 200 , the new header {@link Const#RESPONSE_HEADER_PARAM_ERROR_MESSAGE} with full body as parameter will be add
     */
    public static class HeaderInterceptor
            implements Interceptor {
        @Override
        public com.squareup.okhttp.Response intercept(Chain chain)
                throws IOException {
            Request request = chain.request();

            com.squareup.okhttp.Response response = chain.proceed(request);

            String body = response.body().string();
            int code =  response.code();
            String signature = response.headers().get("X-Sponsorpay-Response-Signature");

            StringBuilder sb = new StringBuilder(body).append(FyberParameterHelper.API_KEY);
            String signatureToCheck = Converter.sha1Hash(sb.toString());
            if(signatureToCheck != null){
                signatureToCheck = signatureToCheck.toLowerCase();
            }
            if(signature != null && signatureToCheck != null && !signature.equals(signatureToCheck)){
                throw new WrongSignatureException();
            }
            if(code != 200) {
                //retrofit remove specific error messages from the body and create instead very generic one "bed request",
                // as work around we store body in the custom header
                return response.newBuilder().addHeader(Const.RESPONSE_HEADER_PARAM_ERROR_MESSAGE,body)
                        .body(ResponseBody.create(response.body().contentType(), body))
                        .build();
            }
            return response.newBuilder()
                    .body(ResponseBody.create(response.body().contentType(), body))
                    .build();
        }
    }
}
