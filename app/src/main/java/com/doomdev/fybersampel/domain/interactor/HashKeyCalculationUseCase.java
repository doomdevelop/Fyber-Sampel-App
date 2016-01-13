package com.doomdev.fybersampel.domain.interactor;

import com.doomdev.fybersampel.data.pojo.request.RequestContent;
import com.doomdev.fybersampel.data.util.Converter;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by and on 13.01.16.
 */
public class HashKeyCalculationUseCase extends UseCase{
    private final Map<String, String> params;
    private final String apikey;
    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    public HashKeyCalculationUseCase(final Map<String, String> params,final String apiKey){
        this.params = params;
        this.apikey = apiKey;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        Observable<RequestContent> observable = Observable.create(new Observable.OnSubscribe<RequestContent>() {
            @Override
            public void call(Subscriber<? super RequestContent> subscriber) {

                RequestContent requestContent = Converter.createHashKex(params,apikey);
                subscriber.onNext(requestContent);
            }
        });

        return observable;
    }
}
