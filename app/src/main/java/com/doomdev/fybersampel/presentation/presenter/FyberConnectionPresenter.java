package com.doomdev.fybersampel.presentation.presenter;

import android.content.Context;
import android.util.Log;

import com.doomdev.fybersampel.data.pojo.FyberResponse;
import com.doomdev.fybersampel.domain.interactor.DefaultSubscriber;
import com.doomdev.fybersampel.domain.interactor.FyberConnectionUseCase;
import com.doomdev.fybersampel.domain.interactor.LoadAdvertisingIdentifierUseCase;
import com.doomdev.fybersampel.domain.interactor.UseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by and on 07.01.16.
 */
public class FyberConnectionPresenter extends Presenter {
    private static final String TAG = FyberConnectionPresenter.class.getSimpleName();

    private FyberConnectionPresenter.View view;
    private static final List<UseCase> useCaseList = new ArrayList<UseCase>();


    public FyberConnectionPresenter(FyberConnectionPresenter.View view) {
        this.view = view;

    }

    private void addUseCase(UseCase useCase) {
        useCaseList.add(useCase);
    }

    private void unsubscribeAllUsecases() {
        for (UseCase useCase : useCaseList) {
            useCase.unsubscribe();
        }
    }

    public void callFyberApi(Map<String, String> params) {
        UseCase useCase = new FyberConnectionUseCase(params);
        useCase.execute(new FyberConnectionSubscriber());
        addUseCase(useCase);
    }

    public void loadAdvertisingIdentifier(Context context) {
        UseCase useCase = new LoadAdvertisingIdentifierUseCase(context);
        useCase.execute(new LoadAdvertisingIdentifierSubscriber());
        addUseCase(useCase);
    }
    @Override
    void resume() {

    }

    @Override
    void pause() {

    }

    @Override
    void destroy() {
        unsubscribeAllUsecases();
    }

    public interface View {
        void onOffersLoaded(FyberResponse offerList);

        void onAdvertisingIdentifierLoaded(String id);

        void hideProgress();

        void showProgress();

        void onError(String message);

        void onNoInternetConnection();
    }

    private final class FyberConnectionSubscriber extends DefaultSubscriber<FyberResponse> {

        @Override
        public void onCompleted() {
            Log.d(TAG, "onCompleted()..");
            FyberConnectionPresenter.this.view.hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG, "onError().." + e.getMessage());
            FyberConnectionPresenter.this.view.hideProgress();
            FyberConnectionPresenter.this.view.onError(((Exception) e).getMessage());
        }

        @Override
        public void onNext(FyberResponse offerList) {
            Log.d(TAG, "onNext().." + offerList.toString());
            FyberConnectionPresenter.this.view.onOffersLoaded(offerList);
        }
    }

    private final class LoadAdvertisingIdentifierSubscriber extends DefaultSubscriber<String> {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Log.d(TAG, "onNext().." + s);
          FyberConnectionPresenter.this.view.onAdvertisingIdentifierLoaded(s);
        }

    }

}
