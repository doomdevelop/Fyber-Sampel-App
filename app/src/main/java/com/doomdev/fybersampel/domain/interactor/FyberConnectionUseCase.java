package com.doomdev.fybersampel.domain.interactor;

import android.util.Log;

import com.doomdev.fybersampel.data.pojo.FyberResponse;
import com.doomdev.fybersampel.data.pojo.request.RequestContent;
import com.doomdev.fybersampel.data.repository.FyberConnectionRepository;

import rx.Observable;

/**
 * Created by and on 09.01.16.
 */
public class FyberConnectionUseCase extends UseCase {
    private static final String TAG = FyberConnectionUseCase.class.getSimpleName();
    private final FyberConnectionRepository fyberConnectionRepository;
    private String signature;
    private final RequestContent requestContent;

    public FyberConnectionUseCase(final RequestContent requestContent) {
        this.requestContent = requestContent;
        this.fyberConnectionRepository = FyberConnectionRepository.getInstance();
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    @Override
    protected Observable<FyberResponse> buildUseCaseObservable() {
       return fyberConnectionRepository.connect(requestContent);
    }
}
