package com.doomdev.fybersampel.data.net;

import com.doomdev.fybersampel.presentation.model.OfferModel;

import rx.Observable;

/**
 * Created by and on 11.01.16.
 */
public class RestApiImpl implements RestApi {

    @Override
    public Observable<OfferModel> getOffer(String appid, String uid, String locale, String ip, String apiKey) {
        return null;
    }
}
