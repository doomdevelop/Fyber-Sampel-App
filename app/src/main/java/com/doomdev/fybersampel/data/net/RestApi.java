package com.doomdev.fybersampel.data.net;

import com.doomdev.fybersampel.presentation.model.OfferModel;

import rx.Observable;

/**
 * Created by and on 10.01.16.
 */
public interface RestApi {
    public static final String BASE_URL = "http://api.fyber.com/feed/v1/";
    Observable<OfferModel> getOffer(String appid,String uid,String locale,String ip,String apiKey);
}
