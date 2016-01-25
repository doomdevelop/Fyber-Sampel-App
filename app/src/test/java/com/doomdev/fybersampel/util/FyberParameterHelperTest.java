package com.doomdev.fybersampel.util;

import com.doomdev.fybersampel.presenter.util.FyberParameterHelper;
import com.doomdev.fybersampel.presenter.util.Params;

import junit.framework.Assert;

import org.junit.Test;

import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by and on 25.01.16.
 */
public class FyberParameterHelperTest {
    private static final String APPID_TEST = "APPID_TEST";

    @Test
    public void onDefaultParameterMapMustContainKeysWithEmptyValues(){
        FyberParameterHelper fyberParameterHelper = new FyberParameterHelper();
        Map<String,String> paraMap = fyberParameterHelper.prepareAndGetParams();
        assertNotNull(paraMap);
        assertThat(paraMap.containsKey(Params.APPID.getKey()), is(true));
        assertThat(paraMap.get(Params.APPID.getKey()).isEmpty(), is(true));
        assertThat(paraMap.get(Params.APPID.getKey()).isEmpty(),is(true));
        assertThat(paraMap.containsKey(Params.PUB0.getKey()),is(true));
        assertThat(paraMap.get(Params.PUB0.getKey()).isEmpty(),is(true));
        assertThat(paraMap.containsKey(Params.APPID.getKey()),is(true));
        assertThat(paraMap.get(Params.APPID.getKey()).isEmpty(),is(true));
        assertThat(paraMap.containsKey(Params.APPID.getKey()),is(true));
        assertThat(paraMap.get(Params.APPID.getKey()).isEmpty(),is(true));
        assertThat(paraMap.containsKey(Params.TIMESTAMP.getKey()),is(true));
        assertThat(paraMap.get(Params.TIMESTAMP.getKey()).length()>0,is(true));//set always by call prepareAndGetParams()
        assertThat(paraMap.containsKey(Params.FORMAT.getKey()),is(true));
        assertThat(paraMap.get(Params.FORMAT.getKey()).length()>0,is(true));//set by Default
        fyberParameterHelper.setParam(Params.APPID, APPID_TEST); // set value for giving key
        assertEquals(paraMap.get(Params.APPID.getKey()),APPID_TEST);//mup must contain value and must be equals
    }

}
