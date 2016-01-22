package com.doomdev.fybersampel.domain.interactor;

import android.content.Context;

import com.doomdev.fybersampel.data.service.android.LoadAdvertisingIdentifierService;


import rx.Observable;
import rx.Scheduler;

/**
 *  An {@link UseCase} loading advertising identifier
 * Created by and on 12.01.16.
 */

public class LoadAdvertisingIdentifierUseCase extends UseCase{

    private Context mContext;
    private LoadAdvertisingIdentifierService mLoadAdvertisingIdentifierService;
    public LoadAdvertisingIdentifierUseCase(Context context){
        this.mLoadAdvertisingIdentifierService = new LoadAdvertisingIdentifierService(context);
        this.mContext = context;
    }

    protected LoadAdvertisingIdentifierUseCase(Context context, LoadAdvertisingIdentifierService loadAdvertisingIdentifierService, Scheduler newThreadScheduler, Scheduler androidThread) {
        super(newThreadScheduler, androidThread);
        this.mLoadAdvertisingIdentifierService = loadAdvertisingIdentifierService;
        this.mContext = context;
    }
    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    @Override
    protected Observable buildUseCaseObservable() {
        return mLoadAdvertisingIdentifierService.load();
    }
}