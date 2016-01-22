package com.doomdev.fybersampel.data.util;

/**
 * This class contain static final attributes useing global in project
 * Created by and on 10.01.16.
 */
public interface Const {
    /**
     * Fyber Api Bae url
     **/
    String BASE_URL = "http://api.fyber.com/feed/v1/";
    /**
     * url suffix, to get offers in json format
     **/
    String FORMAT_JSON = "offers.json?";
    /**
     * complet url
     **/
    String FULL_URL = Const.BASE_URL + FORMAT_JSON;
    /**
     * This is custom header,where the original error message and code will be store
     **/
    String RESPONSE_HEADER_PARAM_ERROR_MESSAGE = "detail_error_message";
    /**
     * This is parameter in the custom header,where the error code will be store
     **/
    String RESPONSE_HEADER_PARAM_ERROR_MESSAGE_JSON_CODE = "code";
    /**
     * This is parameter in the custom header,where the error message will be store
     **/
    String RESPONSE_HEADER_PARAM_ERROR_MESSAGE_JSON_MESSAGE = "message";
    /**
     * the accept parameter application/json
     **/
    String ACCEPT_PARAM = "application/json";
    /**
     * the content parameter application/json
     **/
    String CONTENT_PARAM = "application/json";
}
