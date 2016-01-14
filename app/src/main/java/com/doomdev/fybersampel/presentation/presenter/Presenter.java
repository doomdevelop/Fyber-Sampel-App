package com.doomdev.fybersampel.presentation.presenter;

import android.util.SparseArray;

import com.doomdev.fybersampel.domain.interactor.UseCase;

/**
 * Created by and on 07.01.16.
 */
public abstract class Presenter {
    protected static final SparseArray<UseCase> USE_CASES_LIST = new SparseArray<UseCase>();

    protected void addUseCase(UseCase useCase) {
        USE_CASES_LIST.append(useCase.getId(),useCase);
    }

    protected void removeUseCase(int id){
        USE_CASES_LIST.remove(id);
    }
    protected void unsubscribeAllUseCases() {

        for(int i = 0; i < USE_CASES_LIST.size(); i++) {
            int key = USE_CASES_LIST.keyAt(i);
            USE_CASES_LIST.get(key).unsubscribe();
        }
    }

    abstract void resume();
    abstract void pause();
    abstract void destroy();
}
