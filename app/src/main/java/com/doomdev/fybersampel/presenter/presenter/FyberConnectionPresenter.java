package com.doomdev.fybersampel.presenter.presenter;

import android.content.Context;
import android.util.Log;

import com.doomdev.fybersampel.data.pojo.FyberResponse;
import com.doomdev.fybersampel.data.pojo.request.RequestContent;
import com.doomdev.fybersampel.domain.interactor.DefaultObserver;
import com.doomdev.fybersampel.domain.interactor.GetFyberOffersUseCase;
import com.doomdev.fybersampel.domain.interactor.CalculateHashKeyUseCase;
import com.doomdev.fybersampel.domain.interactor.LoadAdvertisingIdentifierUseCase;
import com.doomdev.fybersampel.domain.interactor.UseCase;
import com.doomdev.fybersampel.presenter.exception.ErrorMessageGenerator;
import com.doomdev.fybersampel.presenter.handler.BufferHandler;
import com.doomdev.fybersampel.presenter.model.OfferModel;
import com.doomdev.fybersampel.presenter.util.FyberModelConverter;
import com.doomdev.fybersampel.presenter.view.Msg;
import com.doomdev.fybersampel.presenter.view.fragment.FyberConnectionFragment;

import java.util.List;
import java.util.Map;

/**
 * This class is a {@link Presenter} for {@link FyberConnectionFragment}.
 * The presenter is forwarding calls in to the logic and back in to the view via given {@link BufferHandler}.
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

    /**
     * Call fyber api with giving params and api key to get offers
     *
     * @param params url parameter
     * @param apiKey the fyber api key
     */
    public void callGetOffersFyberApi(final Map<String, String> params, final String apiKey) {
        UseCase useCase = new CalculateHashKeyUseCase(params, apiKey);
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
                UseCase useCaseRest = new GetFyberOffersUseCase(requestContent);
                useCaseRest.execute(new FyberConnectionObserver());
                addUseCase(useCaseRest);
            }
        });

    }

    /**
     *Execute call {@link LoadAdvertisingIdentifierUseCase} to
     * @param context application context
     */
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
            mHandler.obtainMessage(Msg.HIDE_PROGRESS).sendToTarget();
            if (offerList.getOffers() != null && offerList.getOffers().length > 0) {
                mHandler.obtainMessage(Msg.ON_OFFERS_LOADED, mFyberModelConverter.convertFyberResponse(offerList)).sendToTarget();
            } else {
                mHandler.obtainMessage(Msg.ON_EMPTY_OFFERS_LOADED).sendToTarget();
            }

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
