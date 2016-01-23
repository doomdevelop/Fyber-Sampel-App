package com.doomdev.fybersampel.data.util;

import com.doomdev.fybersampel.data.pojo.request.RequestContent;
import com.doomdev.fybersampel.presenter.util.FyberParameterHelper;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.mockito.Mockito.verify;

/**
 * Created by and on 22.01.16.
 */
public class ConverterTest {
    private FyberParameterHelper fyberParameterHelper;

    @Before
    public void setUp() {
        fyberParameterHelper = new FyberParameterHelper();
        fyberParameterHelper.setDeviceId(null);
        fyberParameterHelper.addTimeStampToTheMap();

    }

    @Test
    public void testCreateHashKex() {
        RequestContent requestContent = Converter.createHashKex(fyberParameterHelper.prepareAndGetParams(), FyberParameterHelper.API_KEY);
        Assert.assertNotNull(requestContent);
        Assert.assertTrue(!requestContent.getUrl().contains(FyberParameterHelper.API_KEY));
    }

    @Test
    public void testCreateHashKexWithNoMap() {
        Map<String, String> emptyMap = fyberParameterHelper.prepareAndGetParams();
        emptyMap.clear();
        RequestContent requestContent = Converter.createHashKex(emptyMap, FyberParameterHelper.API_KEY);
        Assert.assertNotNull(requestContent);
    }

    @Test
    public void testUrlWithNoParametersMustHaveSignature() {
        Map<String, String> emptyMap = fyberParameterHelper.prepareAndGetParams();
        emptyMap.clear();
        RequestContent requestContent = Converter.createHashKex(emptyMap, FyberParameterHelper.API_KEY);
        String[] urlAndkey = requestContent.getUrl().split("&");
        Assert.assertTrue(urlAndkey[0].equals(Const.FULL_URL));
        Assert.assertNotNull(urlAndkey[1]);//has signature

    }

    @Test
    public void testCreateHashKexWithNull() {
        RequestContent requestContent = Converter.createHashKex(null, FyberParameterHelper.API_KEY);
        Assert.assertNull(requestContent);
    }

    @Test
    public void testSha1Hash() {
        Assert.assertNull(Converter.sha1Hash(null));

    }

}
