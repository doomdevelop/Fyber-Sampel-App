package com.doomdev.fybersampel.data.service.rx;


import com.doomdev.fybersampel.data.pojo.FyberResponse;


import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Url;
import rx.Observable;

/**
 * Interface represent Api service call
 */
public interface FyberOffersServiceRx {

    @GET
    Observable<FyberResponse> connect(@Header("Accept") String accept,@Header("Content-Type") String content,@Header ("X-Sponsorpay-Response-Signature") String signature, @Url String url);

}
