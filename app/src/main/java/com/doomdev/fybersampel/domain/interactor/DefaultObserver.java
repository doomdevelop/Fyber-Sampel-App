package com.doomdev.fybersampel.domain.interactor;

/**
 * Created by and on 11.01.16.
 */

import rx.Observer;

/**
 * Default subscriber base class to be used whenever you want default error handling.
 */
public class DefaultObserver<T> implements Observer<T> {
    private int useCaseId = 0;

    public void setUseCaseId(int useCaseId){
        this.useCaseId = useCaseId;
    }
    public int getUseCaseId(){
        return useCaseId;
    }

    @Override public void onCompleted() {
        // no-op by default.
    }

    @Override public void onError(Throwable e) {
        // no-op by default.
    }

    @Override public void onNext(T t) {
        // no-op by default.
    }
}