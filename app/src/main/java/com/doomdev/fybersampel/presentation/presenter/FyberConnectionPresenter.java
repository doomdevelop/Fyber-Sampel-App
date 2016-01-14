package com.doomdev.fybersampel.presentation.presenter;

import android.content.Context;
import android.util.Log;

import com.doomdev.fybersampel.data.pojo.FyberResponse;
import com.doomdev.fybersampel.data.pojo.request.RequestContent;
import com.doomdev.fybersampel.domain.interactor.DefaultSubscriber;
import com.doomdev.fybersampel.domain.interactor.FyberConnectionUseCase;
import com.doomdev.fybersampel.domain.interactor.HashKeyCalculationUseCase;
import com.doomdev.fybersampel.domain.interactor.LoadAdvertisingIdentifierUseCase;
import com.doomdev.fybersampel.domain.interactor.UseCase;

import java.util.Map;

/**
 * Created by and on 07.01.16.
 */
public class FyberConnectionPresenter extends Presenter {
    private static final String TAG = FyberConnectionPresenter.class.getSimpleName();

    private FyberConnectionPresenter.View view;



    public FyberConnectionPresenter(FyberConnectionPresenter.View view) {
        this.view = view;

    }


    public void callFyberApi(final Map<String, String> params,final String apiKey) {
        UseCase useCase = new HashKeyCalculationUseCase(params,apiKey);
        useCase.execute(new DefaultSubscriber<RequestContent>() {
            @Override
            public void onCompleted() {
                super.onCompleted();
                removeUseCase(getUseCaseId());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }

            @Override
            public void onNext(RequestContent requestContent) {
                super.onNext(requestContent);
                UseCase useCaseRest = new FyberConnectionUseCase(requestContent);
                useCaseRest.execute(new FyberConnectionSubscriber());
                addUseCase(useCaseRest);
            }
        });

    }

    public void loadAdvertisingIdentifier(Context context) {
        UseCase useCase = new LoadAdvertisingIdentifierUseCase(context);
        useCase.execute(new LoadAdvertisingIdentifierSubscriber());
        addUseCase(useCase);
    }
    @Override
    public void resume() {

    }

    @Override
   public void pause() {

    }

    @Override
   public void destroy() {
        unsubscribeAllUseCases();
        USE_CASES_LIST.clear();
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
            view.hideProgress();
            removeUseCase(getUseCaseId());
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            Log.d(TAG, "onError().." + e.getMessage()+" "+e.toString());
            view.hideProgress();
            view.onError(((Exception) e).getMessage());
        }

        @Override
        public void onNext(FyberResponse offerList) {
            Log.d(TAG, "onNext().." + offerList.toString());
            view.onOffersLoaded(offerList);
        }
    }

    private final class LoadAdvertisingIdentifierSubscriber extends DefaultSubscriber<String> {
        @Override
        public void onCompleted() {
            removeUseCase(getUseCaseId());
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            Log.d(TAG, "onNext().." + s);
          view.onAdvertisingIdentifierLoaded(s);
        }

    }

}
