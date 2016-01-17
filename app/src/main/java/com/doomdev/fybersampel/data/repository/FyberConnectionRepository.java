package com.doomdev.fybersampel.data.repository;

import com.doomdev.fybersampel.data.pojo.request.RequestContent;
import com.doomdev.fybersampel.data.service.rx.FyberConnectionServiceRx;
import com.doomdev.fybersampel.data.net.RestApi;
import com.doomdev.fybersampel.data.service.rx.RestServiceRxGenerator;
import com.doomdev.fybersampel.data.pojo.FyberResponse;

import rx.Observable;

/**
 * Created by and on 11.01.16.
 */
public class FyberConnectionRepository {
    private static FyberConnectionRepository ourInstance = new FyberConnectionRepository();

    public static FyberConnectionRepository getInstance() {
        return ourInstance;
    }
    //RequestContent
//    public Observable<FyberResponse> connect(Map<String, String> params,String signature){
//       return  RestServiceRxGenerator.createService(FyberConnectionServiceRx.class, RestApi.BASE_URL).connect(FyberConnectionServiceRx.ACCEPT_PARAM,FyberConnectionServiceRx.CONTENT_PARAM,signature, params);
//    }
    public Observable<FyberResponse> connect(RequestContent requestContent){
        return  RestServiceRxGenerator.createService(FyberConnectionServiceRx.class, RestApi.BASE_URL).connect(FyberConnectionServiceRx.ACCEPT_PARAM,FyberConnectionServiceRx.CONTENT_PARAM,requestContent.getSignature(), requestContent.getUrl());
    }

}
