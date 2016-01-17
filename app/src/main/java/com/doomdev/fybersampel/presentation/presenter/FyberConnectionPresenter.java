package com.doomdev.fybersampel.presentation.presenter;

import android.content.Context;
import android.util.Log;

import com.doomdev.fybersampel.data.net.RestApi;
import com.doomdev.fybersampel.data.pojo.FyberResponse;
import com.doomdev.fybersampel.data.pojo.request.RequestContent;
import com.doomdev.fybersampel.domain.interactor.DefaultObserver;
import com.doomdev.fybersampel.domain.interactor.FyberConnectionUseCase;
import com.doomdev.fybersampel.domain.interactor.HashKeyCalculationUseCase;
import com.doomdev.fybersampel.domain.interactor.LoadAdvertisingIdentifierUseCase;
import com.doomdev.fybersampel.domain.interactor.UseCase;
import com.doomdev.fybersampel.presentation.exception.ErrorMessageGenerator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import retrofit.HttpException;

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
        useCase.execute(new DefaultObserver<RequestContent>() {
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
                useCaseRest.execute(new FyberConnectionObserver());
                addUseCase(useCaseRest);
            }
        });

    }

    public void loadAdvertisingIdentifier(Context context) {
        UseCase useCase = new LoadAdvertisingIdentifierUseCase(context);
        useCase.execute(new LoadAdvertisingIdentifierObserver());
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

    private final class FyberConnectionObserver extends DefaultObserver<FyberResponse> {

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
            int code = 0;
            if(e instanceof HttpException){
                HttpException exception = ((HttpException) e);
                String errorMessage = exception.response().headers().get(RestApi.RESPONSE_HEADER_PARAM_ERROR_MESSAGE);
                Log.d(TAG,"IOException code: "+exception.code());
                Log.d(TAG,"IOException message: "+exception.message());
                Log.d(TAG,"IOException errorBody: "+exception.response().errorBody());
                Log.d(TAG, "IOException raw.body: " + exception.response().raw().body());

            }else {
                Log.d(TAG, e.getClass().getSimpleName());
            }
            view.hideProgress();
            view.onError(ErrorMessageGenerator.generateMessage(e));
        }

        @Override
        public void onNext(FyberResponse offerList) {
            Log.d(TAG, "onNext().." + offerList.toString());
            view.onOffersLoaded(offerList);
        }
    }

    private final class LoadAdvertisingIdentifierObserver extends DefaultObserver<String> {
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
