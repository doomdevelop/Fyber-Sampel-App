package com.doomdev.fybersampel.domain.interactor;

import android.util.Log;

import com.doomdev.fybersampel.data.pojo.FyberResponse;
import com.doomdev.fybersampel.data.repository.FyberConnectionRepository;

import java.util.Map;

import rx.Observable;

/**
 * Created by and on 09.01.16.
 */
public class FyberConnectionUseCase extends UseCase{
    private static final String TAG = FyberConnectionUseCase.class.getSimpleName();
    private final FyberConnectionRepository fyberConnectionRepository;
    private final Map<String,String> paams;
    public FyberConnectionUseCase(Map<String, String> paams) {
        this.paams = paams;
        this.fyberConnectionRepository =  FyberConnectionRepository.getInstance();
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    @Override
    protected Observable<FyberResponse> buildUseCaseObservable() {
        Observable<FyberResponse> response = fyberConnectionRepository.connect(this.paams);
        Log.d(TAG,"buildUseCaseObservable, get response: "+response.toString());
       return response;
    }
}
