package com.doomdev.fybersampel.domain.interactor;

import com.doomdev.fybersampel.presenter.util.FyberParameterDemoHelper;
import com.doomdev.fybersampel.presenter.view.fragment.FyberConnectionFragment;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Scheduler;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Test for {@link CalculateHashKeyUseCase} class
 * Created by and on 21.01.16.
 */
public class CalculateHashKeyUseCaseTest {

    private CalculateHashKeyUseCase calculateHashKeyUseCase;
    @Mock
    private Scheduler newThreadScheduler = Schedulers.test();
    @Mock
    private Scheduler androidThread = Schedulers.test();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        FyberParameterDemoHelper fyberParameterDemoHelper = new FyberParameterDemoHelper();
        fyberParameterDemoHelper.setDeviceId("123456789");
        fyberParameterDemoHelper.addTimeStampToTheMap();
        calculateHashKeyUseCase = new CalculateHashKeyUseCase(fyberParameterDemoHelper.prepareAndGetParams(), FyberConnectionFragment.API_KEY, newThreadScheduler,
                androidThread);
    }

    @Test
    public void testGetUserListUseCaseObservableHappyCase() {
        calculateHashKeyUseCase.buildUseCaseObservable();
        verifyZeroInteractions(newThreadScheduler);
        verifyZeroInteractions(androidThread);
    }
}

