package com.doomdev.fybersampel.presenter.util.presenter;

import com.doomdev.fybersampel.presenter.handler.BufferHandler;
import com.doomdev.fybersampel.presenter.presenter.FyberConnectionPresenter;
import com.doomdev.fybersampel.presenter.util.FyberParameterHelper;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

/**
 * Created by and on 23.01.16.
 */
public class FyberConnectionPresenterTest {
    private FyberParameterHelper mFyberParameterHelper;

    private FyberConnectionPresenter mFyberConnectionPresenter;
    @Mock
    private BufferHandler mBufferHandler;
    @Mock
    private FyberConnectionPresenter.View mFyberConnectionView;

    @Before
    public void setUp(){
        this.mFyberParameterHelper = new FyberParameterHelper();

        mFyberParameterHelper.addTimeStampToTheMap();
        this.mFyberConnectionPresenter = new FyberConnectionPresenter(mBufferHandler);
    }
    @Test
    public void testLoadOffers(){
        this.mFyberConnectionPresenter.callGetOffersFyberApi(mFyberParameterHelper.prepareAndGetParams(), null);
//        verify(mFyberConnectionView).onError();
    }
}
