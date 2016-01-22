package com.doomdev.fybersampel.domain.interactor;

import android.support.v7.recyclerview.BuildConfig;

import com.doomdev.fybersampel.CustomTestRunner;
import com.doomdev.fybersampel.data.pojo.request.RequestContent;
import com.doomdev.fybersampel.data.service.android.LoadAdvertisingIdentifierService;
import com.doomdev.fybersampel.presenter.view.activity.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import rx.Scheduler;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Test for {@link LoadAdvertisingIdentifierUseCase} class
 * Created by and on 21.01.16.
 */
@RunWith(CustomTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoadAdvertisingIdentifierUseCaseTest {

    private MainActivity activity;
    @Mock
    private Scheduler newThreadScheduler = Schedulers.test();
    @Mock
    private Scheduler androidThread = Schedulers.test();
    @Mock
    private LoadAdvertisingIdentifierService loadAdvertisingIdentifierService;
    @Mock
    private RequestContent requestContent;
    @Mock
    private LoadAdvertisingIdentifierUseCase loadAdvertisingIdentifierUseCase;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(MainActivity.class);
        MockitoAnnotations.initMocks(this);
        loadAdvertisingIdentifierUseCase = new LoadAdvertisingIdentifierUseCase(activity.getApplicationContext(), loadAdvertisingIdentifierService, newThreadScheduler, androidThread);
    }

    @Test
    public void testLoadAdvertisingIdentifierUseCaseTestHappyCase() {
        loadAdvertisingIdentifierUseCase.buildUseCaseObservable();
        verify(loadAdvertisingIdentifierService).load();
        verifyZeroInteractions(newThreadScheduler);
        verifyZeroInteractions(androidThread);
    }
}
