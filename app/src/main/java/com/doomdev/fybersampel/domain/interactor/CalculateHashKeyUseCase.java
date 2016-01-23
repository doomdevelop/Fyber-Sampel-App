package com.doomdev.fybersampel.domain.interactor;

import com.doomdev.fybersampel.data.pojo.request.RequestContent;
import com.doomdev.fybersampel.data.util.Converter;

import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;

/**
 * An {@link UseCase} creating valid url and signature for api request.
 *
 * Created by and on 13.01.16.
 */
public class CalculateHashKeyUseCase extends UseCase {
    private final Map<String, String> params;
    private final String apikey;
    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    public CalculateHashKeyUseCase(final Map<String, String> params, final String apiKey) {
        this.params = params;
        this.apikey = apiKey;
    }

    protected CalculateHashKeyUseCase(final Map<String, String> params, final String apiKey, Scheduler newThreadScheduler, Scheduler androidThread) {
        super(newThreadScheduler, androidThread);
        this.params = params;
        this.apikey = apiKey;
    }
    @Override
    protected Observable buildUseCaseObservable() {
        return Observable.create(new Observable.OnSubscribe<RequestContent>() {
            @Override
            public void call(Subscriber<? super RequestContent> subscriber) {

                RequestContent requestContent = Converter.createHashKex(params,apikey);
                subscriber.onNext(requestContent);
            }
        });
    }
}
