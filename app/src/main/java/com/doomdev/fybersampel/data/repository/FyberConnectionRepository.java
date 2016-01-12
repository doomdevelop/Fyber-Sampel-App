package com.doomdev.fybersampel.data.repository;

import com.doomdev.fybersampel.data.service.rx.FyberConnectionServiceRx;
import com.doomdev.fybersampel.data.net.RestApi;
import com.doomdev.fybersampel.data.net.RestApiImpl;
import com.doomdev.fybersampel.data.service.rx.RestServiceRxGenerator;
import com.doomdev.fybersampel.data.pojo.FyberResponse;

import java.util.Map;

import rx.Observable;

/**
 * Created by and on 11.01.16.
 */
public class FyberConnectionRepository {
    private static FyberConnectionRepository ourInstance = new FyberConnectionRepository();

    public static FyberConnectionRepository getInstance() {
        return ourInstance;
    }

    public Observable<FyberResponse> connect(Map<String, String> params){
       return  RestServiceRxGenerator.createService(FyberConnectionServiceRx.class, RestApi.BASE_URL).connect(params);
    }

}
