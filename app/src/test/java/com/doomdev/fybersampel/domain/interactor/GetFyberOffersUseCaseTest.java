package com.doomdev.fybersampel.domain.interactor;

import com.doomdev.fybersampel.data.pojo.request.RequestContent;
import com.doomdev.fybersampel.data.repository.FyberConnectionRepository;
import com.doomdev.fybersampel.presenter.util.FyberParameterHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Scheduler;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Test for {@link GetFyberOffersUseCase} class
 * Created by and on 21.01.16.
 */
public class GetFyberOffersUseCaseTest {

    private GetFyberOffersUseCase getFyberOffersUseCase;
    @Mock
    private Scheduler newThreadScheduler = Schedulers.test();
    @Mock
    private Scheduler androidThread = Schedulers.test();
    @Mock
    private FyberConnectionRepository fyberConnectionRepository;
    @Mock
    private RequestContent requestContent;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        FyberParameterHelper fyberParameterHelper = new FyberParameterHelper();
        fyberParameterHelper.setDeviceId("123456789");
        fyberParameterHelper.addTimeStampToTheMap();
        when(requestContent.getSignature()).thenReturn("");
        when(requestContent.getUrl()).thenReturn("");
        getFyberOffersUseCase = new GetFyberOffersUseCase(requestContent, fyberConnectionRepository, newThreadScheduler, androidThread);
    }

    @Test
    public void testGetFyberOffersUseCaseTestHappyCase() {
        getFyberOffersUseCase.buildUseCaseObservable();
        verify(fyberConnectionRepository).getFyberOffers(requestContent);
        verifyZeroInteractions(newThreadScheduler);
        verifyZeroInteractions(androidThread);
    }
}
