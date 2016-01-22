package com.doomdev.fybersampel.data.pojo.request;

/**
 * The content of the request containing full url with concatenated  parameters and signature
 * Created by and on 13.01.16.
 */
public class RequestContent {
    private final String url;
    private final String signature;

    public RequestContent(String url, String signature) {
        this.url = url;
        this.signature = signature;
    }

    /**
     * @return Full url with concatenated parameters
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @return signature for request
     */
    public String getSignature() {
        return signature;
    }


}
