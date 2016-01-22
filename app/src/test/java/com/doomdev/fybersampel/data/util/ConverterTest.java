package com.doomdev.fybersampel.data.util;

import com.doomdev.fybersampel.data.pojo.request.RequestContent;
import com.doomdev.fybersampel.presenter.util.FyberParameterDemoHelper;
import com.doomdev.fybersampel.presenter.view.fragment.FyberConnectionFragment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.mockito.Mockito.verify;

/**
 * Created by and on 22.01.16.
 */
public class ConverterTest {
    private FyberParameterDemoHelper fyberParameterDemoHelper;

    @Before
    public void setUp() {
        fyberParameterDemoHelper = new FyberParameterDemoHelper();
        fyberParameterDemoHelper.setDeviceId(null);
        fyberParameterDemoHelper.addTimeStampToTheMap();

    }

    @Test
    public void testCreateHashKex() {
        RequestContent requestContent = Converter.createHashKex(fyberParameterDemoHelper.prepareAndGetParams(), FyberConnectionFragment.API_KEY);
        Assert.assertNotNull(requestContent);
        Assert.assertTrue(!requestContent.getUrl().contains(FyberConnectionFragment.API_KEY));
    }

    @Test
    public void testCreateHashKexWithNoMap() {
        Map<String, String> emptyMap = fyberParameterDemoHelper.prepareAndGetParams();
        emptyMap.clear();
        RequestContent requestContent = Converter.createHashKex(emptyMap, FyberConnectionFragment.API_KEY);
        Assert.assertNotNull(requestContent);
    }

    @Test
    public void testUrlWithNoParametersMustHaveSignature() {
        Map<String, String> emptyMap = fyberParameterDemoHelper.prepareAndGetParams();
        emptyMap.clear();
        RequestContent requestContent = Converter.createHashKex(emptyMap, FyberConnectionFragment.API_KEY);
        String[] urlAndkey = requestContent.getUrl().split("&");
        Assert.assertTrue(urlAndkey[0].equals(Const.FULL_URL));
        Assert.assertNotNull(urlAndkey[1]);//has signature

    }

    @Test
    public void testCreateHashKexWithNull() {
        RequestContent requestContent = Converter.createHashKex(null, FyberConnectionFragment.API_KEY);
        Assert.assertNull(requestContent);
    }

    @Test
    public void testSha1Hash() {
        Assert.assertNull(Converter.sha1Hash(null));

    }

}
