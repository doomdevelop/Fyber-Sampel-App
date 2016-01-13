package com.doomdev.fybersampel.data.pojo.request;

/**
 * Created by and on 13.01.16.
 */
public class RequestContent {
    private final String url;
    private final String signature;

    public RequestContent(String url, String signature) {
        this.url = url;
        this.signature = signature;
    }

    public String getUrl() {
        return url;
    }

    public String getSignature() {
        return signature;
    }


}
