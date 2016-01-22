package com.doomdev.fybersampel.data.repository;

import android.support.annotation.NonNull;

import com.doomdev.fybersampel.data.pojo.request.RequestContent;
import com.doomdev.fybersampel.data.service.rx.FyberOffersServiceRx;
import com.doomdev.fybersampel.data.util.Const;
import com.doomdev.fybersampel.data.service.rx.RestServiceRxGenerator;
import com.doomdev.fybersampel.data.pojo.FyberResponse;

import rx.Observable;

/**
 * Singleton Repository class providing currently only access an api services.
 *
 * Created by and on 11.01.16.
 */
public class FyberConnectionRepository {
    //todo: currently this class is very simple. In case having DataBase, caching system would be more useful to implement repository pattern here
    private static FyberConnectionRepository ourInstance = new FyberConnectionRepository();

    public static FyberConnectionRepository getInstance() {
        return ourInstance;
    }

    public Observable<FyberResponse> getFyberOffers(@NonNull RequestContent requestContent) {
        return RestServiceRxGenerator.createService(FyberOffersServiceRx.class, Const.BASE_URL).connect(Const.ACCEPT_PARAM, Const.CONTENT_PARAM, requestContent.getSignature(), requestContent.getUrl());
    }

}
