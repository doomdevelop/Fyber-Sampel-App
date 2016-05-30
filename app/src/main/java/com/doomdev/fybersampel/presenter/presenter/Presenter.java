package com.doomdev.fybersampel.presenter.presenter;

import android.util.SparseArray;

import com.doomdev.fybersampel.domain.interactor.UseCase;
import com.doomdev.fybersampel.presenter.handler.BufferHandler;

/**
 * Abstract class giving base structure for interaction between view and logic.
 * Presenter will pass calls from the view in to the specific {@link UseCase} and call back view via handler {@link BufferHandler}
 * Created by and on 07.01.16.
 */
public abstract class Presenter<T extends UseCase> {
    protected final SparseArray<T> USE_CASES_LIST = new SparseArray<>();

    /**
     *  Add useCase in to the list with currently active use cases
     * @param useCase to add in to the list with currently active use cases
     */
    protected void addUseCase(T useCase) {
        USE_CASES_LIST.append(useCase.getId(),useCase);
    }

    /**
     *  Remove useCase with giving id from the list with currently active use cases
     * @param id of ucecase to remove
     */
    protected void removeUseCase(int id) {
        USE_CASES_LIST.remove(id);
    }

    /**
     * Unsubscribe all active useCases
     */
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
