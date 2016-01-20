package com.doomdev.fybersampel.presentation.presenter;

import android.content.Context;
import android.util.Log;

import com.doomdev.fybersampel.data.pojo.FyberResponse;
import com.doomdev.fybersampel.data.pojo.request.RequestContent;
import com.doomdev.fybersampel.domain.interactor.DefaultObserver;
import com.doomdev.fybersampel.domain.interactor.FyberConnectionUseCase;
import com.doomdev.fybersampel.domain.interactor.HashKeyCalculationUseCase;
import com.doomdev.fybersampel.domain.interactor.LoadAdvertisingIdentifierUseCase;
import com.doomdev.fybersampel.domain.interactor.UseCase;
import com.doomdev.fybersampel.presentation.exception.ErrorMessageGenerator;
import com.doomdev.fybersampel.presentation.handler.BufferHandler;
import com.doomdev.fybersampel.presentation.model.OfferModel;
import com.doomdev.fybersampel.presentation.util.FyberModelConverter;
import com.doomdev.fybersampel.presentation.view.Msg;
import com.doomdev.fybersampel.presentation.view.fragment.FyberConnectionFragment;

import java.util.List;
import java.util.Map;

/**
 * This class implements methods
 * Created by and on 07.01.16.
 *
 */
public class FyberConnectionPresenter extends Presenter {
    private static final String TAG = FyberConnectionPresenter.class.getSimpleName();
    private BufferHandler mHandler;
    private FyberModelConverter mFyberModelConverter;


    public FyberConnectionPresenter(BufferHandler handler) {
        this.mHandler = handler;
        this.mFyberModelConverter = new FyberModelConverter();
    }

    public void callFyberApi(final Map<String, String> params, final String apiKey) {
        UseCase useCase = new HashKeyCalculationUseCase(params, apiKey);
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
        mHandler.resume();
    }

    @Override
    public void pause() {
        mHandler.pause();
    }

    @Override
    public void destroy() {
        unsubscribeAllUseCases();
        mHandler.destroy();
        USE_CASES_LIST.clear();
    }

    public interface View {
        void onOffersLoaded(List<OfferModel> offerModelList);

        void onAdvertisingIdentifierLoaded(String id);

        void hideProgress();

        void onError(String message);

        void onNoInternetConnection();
    }


    private final class FyberConnectionObserver extends DefaultObserver<FyberResponse> {

        @Override
        public void onCompleted() {
            Log.d(TAG, "onCompleted()..");

            removeUseCase(getUseCaseId());
        }

        @Override
        public void onError(Throwable e) {
//            e.printStackTrace();
            Log.d(TAG, this.getClass().getSimpleName()+" onError().." + e.getMessage() + " " + e.toString());
            mHandler.obtainMessage(Msg.HIDE_PROGRESS).sendToTarget();
            mHandler.obtainMessage(Msg.ON_ERROR, ErrorMessageGenerator.generateMessage(e)).sendToTarget();
        }

        @Override
        public void onNext(FyberResponse offerList) {
            Log.d(TAG, "onNext().." + offerList.toString());
            mHandler.obtainMessage(Msg.ON_OFFERS_LOADED, mFyberModelConverter.convertOffers(offerList)).sendToTarget();
        }
    }

    private final class LoadAdvertisingIdentifierObserver extends DefaultObserver<String> {
        @Override
        public void onCompleted() {
            removeUseCase(getUseCaseId());
        }

        @Override
        public void onError(Throwable e) {
            Log.d(TAG,this.getClass().getSimpleName()+" onError().." + e.getMessage() + " " + e.toString());
            mHandler.obtainMessage(Msg.HIDE_PROGRESS).sendToTarget();
            mHandler.obtainMessage(Msg.ON_ERROR, ErrorMessageGenerator.generateMessage(e)).sendToTarget();
        }

        @Override
        public void onNext(String s) {
            Log.d(TAG, this.getClass().getSimpleName()+ " onNext().." + s);
            mHandler.obtainMessage(Msg.ON_ADVERTISING_IDENTIFIER_LOADED, s).sendToTarget();
        }
    }
}
