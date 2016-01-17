package com.doomdev.fybersampel.data.net;

import com.doomdev.fybersampel.presentation.model.OfferModel;

import rx.Observable;

/**
 * Created by and on 10.01.16.
 */
public interface RestApi {
    public static final String BASE_URL = "http://api.fyber.com/feed/v1/";
    public static final String FORMAT_JSON = "offers.json?";
    public static final String URL = RestApi.BASE_URL + FORMAT_JSON;
    public static final String RESPONSE_HEADER_PARAM_ERROR_MESSAGE ="detail_error_message";
    public static final String RESPONSE_HEADER_PARAM_ERROR_MESSAGE_JSON_CODE ="code";
    public static final String RESPONSE_HEADER_PARAM_ERROR_MESSAGE_JSON_MESSAGE ="message";
}
