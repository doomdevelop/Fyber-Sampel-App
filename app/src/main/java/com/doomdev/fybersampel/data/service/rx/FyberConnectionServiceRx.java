package com.doomdev.fybersampel.data.service.rx;


import com.doomdev.fybersampel.data.pojo.FyberResponse;
import com.doomdev.fybersampel.presentation.model.OfferModel;

import java.util.List;
import java.util.Map;


import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import retrofit.http.Url;
import rx.Observable;


public interface FyberConnectionServiceRx{
    public final static String ACCEPT_PARAM = "application/json";
    public final static String CONTENT_PARAM = "application/json";

    @GET
    Observable<FyberResponse> connect(@Header("Accept") String accept,@Header("Content-Type") String content,@Header ("X-Sponsorpay-Response-Signature") String signature, @Url String url);

}
