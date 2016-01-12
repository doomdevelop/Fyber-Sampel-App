package com.doomdev.fybersampel.domain.interactor;

import android.content.Context;

import com.doomdev.fybersampel.data.service.android.LoadAdvertisingIdentifierService;

import rx.Observable;

/**
 * Created by and on 12.01.16.
 */
public class LoadAdvertisingIdentifierUseCase extends UseCase{

    private Context context;
    public LoadAdvertisingIdentifierUseCase(Context context){
        this.context = context;
    }
    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    @Override
    protected Observable buildUseCaseObservable() {
       return new LoadAdvertisingIdentifierService(context).load();
    }
}