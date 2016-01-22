package com.doomdev.fybersampel.data.service.android;

import android.support.v7.recyclerview.BuildConfig;

import com.doomdev.fybersampel.CustomTestRunner;
import com.doomdev.fybersampel.presenter.view.activity.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import rx.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test for {@link LoadAdvertisingIdentifierService}
 * Created by and on 22.01.16.
 */
@RunWith(CustomTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class LoadAdvertisingIdentifierServiceTest {

    @Mock
    private LoadAdvertisingIdentifierService loadAdvertisingIdentifierService;
    MainActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(MainActivity.class);
        MockitoAnnotations.initMocks(this);
        loadAdvertisingIdentifierService = new LoadAdvertisingIdentifierService(activity.getApplicationContext());
    }

    @Test
    public void testLoadAdvertisingIdentifierServiceRetrunObservable() {

        when(loadAdvertisingIdentifierService.load()).thenReturn(Observable.<String>empty());
    }

}
