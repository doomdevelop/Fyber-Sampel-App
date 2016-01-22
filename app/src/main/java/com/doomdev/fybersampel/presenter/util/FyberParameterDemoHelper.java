package com.doomdev.fybersampel.presenter.util;

import java.util.Map;
import java.util.TreeMap;

/**
 * Class to store temporary map with url parameters.
 * Created by and on 17.01.16.
 */
public class FyberParameterDemoHelper {
    private Map<String, String> mMapParams = new TreeMap<>();
    private String deviceId = null;

    public FyberParameterDemoHelper(){
        createMap();
    }

    private void createMap() {
        mMapParams.clear();
        mMapParams.put(Params.FORMAT.getKey(), Params.FORMAT.getValue());
        mMapParams.put(Params.APPID.getKey(), Params.APPID.getValue());
        mMapParams.put(Params.UID.getKey(),  Params.UID.getValue());
        mMapParams.put(Params.LOCALE.getKey(), Params.LOCALE.getValue());
        mMapParams.put(Params.IP.getKey(), Params.IP.getValue());
        mMapParams.put(Params.OFFER_TYPES.getKey(),Params.OFFER_TYPES.getValue());
        mMapParams.put(Params.PUB0.getKey(), Params.PUB0.getValue());
        mMapParams.put(Params.TIMESTAMP.getKey(),"");
        mMapParams.put(Params.DEVICE_ID.getKey(), "");
    }

    public void setDeviceId(String deviceid){
        this.deviceId = deviceid;
        mMapParams.put(Params.DEVICE_ID.getKey(),this.deviceId );
    }
    public void addTimeStampToTheMap(){
        mMapParams.put(Params.TIMESTAMP.getKey(),String.valueOf(System.currentTimeMillis() / 1000L));
    }

    public Map<String,String>  prepareAndGetParams(){
        addTimeStampToTheMap();
        return mMapParams;
    }

    public void setParam(Params param, String value) {
        switch (param) {
            case FORMAT:
                mMapParams.put(Params.FORMAT.getKey(), value);
                break;
            case APPID:
                mMapParams.put(Params.APPID.getKey(), value);
                break;
            case UID:
                mMapParams.put(Params.UID.getKey(), value);
                break;
            case LOCALE:
                mMapParams.put(Params.LOCALE.getKey(), value);
                break;
            case IP:
                mMapParams.put(Params.IP.getKey(), value);
                break;
            case OFFER_TYPES:
                mMapParams.put(Params.OFFER_TYPES.getKey(), value);
                break;
            case TIMESTAMP:
                mMapParams.put(Params.TIMESTAMP.getKey(), value);
                break;
            case DEVICE_ID:
                mMapParams.put(Params.DEVICE_ID.getKey(), value);
                break;
            case PUB0:
                mMapParams.put(Params.PUB0.getKey(), value);
                break;
        }
    }

}
