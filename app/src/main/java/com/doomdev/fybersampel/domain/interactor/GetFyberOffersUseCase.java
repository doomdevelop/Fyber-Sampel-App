package com.doomdev.fybersampel.domain.interactor;

import com.doomdev.fybersampel.data.pojo.FyberResponse;
import com.doomdev.fybersampel.data.pojo.request.RequestContent;
import com.doomdev.fybersampel.data.repository.FyberConnectionRepository;

import rx.Observable;
import rx.Scheduler;

/**
 * An {@link UseCase} call fyber api to get offers
 * Created by and on 09.01.16.
 */
public class GetFyberOffersUseCase extends UseCase {
    private static final String TAG = GetFyberOffersUseCase.class.getSimpleName();
    private final FyberConnectionRepository fyberConnectionRepository;
    private String signature;
    private final RequestContent requestContent;

    public GetFyberOffersUseCase(final RequestContent requestContent) {
        this.requestContent = requestContent;
        this.fyberConnectionRepository = FyberConnectionRepository.getInstance();
    }

    public GetFyberOffersUseCase(final RequestContent requestContent, FyberConnectionRepository fyberConnectionRepository, Scheduler newThreadScheduler, Scheduler androidThread) {
        super(newThreadScheduler, androidThread);
        this.requestContent = requestContent;
        this.fyberConnectionRepository = fyberConnectionRepository;
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    @Override
    protected Observable<FyberResponse> buildUseCaseObservable() {
        return fyberConnectionRepository.getFyberOffers(requestContent);
    }
}
