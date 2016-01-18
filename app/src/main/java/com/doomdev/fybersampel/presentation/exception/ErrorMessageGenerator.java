package com.doomdev.fybersampel.presentation.exception;

import com.doomdev.fybersampel.data.exception.WrongSignatureException;
import com.doomdev.fybersampel.data.net.RestApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit.HttpException;

/**
 * Created by and on 17.01.16.
 */
public class ErrorMessageGenerator {
    public static String generateMessage(Throwable e) {
        if (e instanceof HttpException) {
            return generateDetailMessage((HttpException) e);
        }
        return e.getMessage();
    }


    private static String generateDetailMessage(HttpException e) {
        int code;

        String error =  getMessageFromCustomHeaderParam(e);
        if(error != null){
            return error;
        }
        try {
            error = new JSONObject(e.response().errorBody().source().readUtf8()).getString("message");
        } catch (JSONException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        if(error != null && error.length()>0){
            return error;
        }
        code = e.response().code();
        //TODO: add more specifics error messages for different codes
        return e.getMessage();
    }

    private static String getMessageFromCustomHeaderParam(HttpException e) {
        String responseErrorMessage = null;
        String responseErrorCode = null;
        StringBuilder sb = new StringBuilder();
        try {
            String errorMessage = e.response().headers().get(RestApi.RESPONSE_HEADER_PARAM_ERROR_MESSAGE);
            JSONObject json = new JSONObject(errorMessage);
            responseErrorCode = json.getString(RestApi.RESPONSE_HEADER_PARAM_ERROR_MESSAGE_JSON_CODE);
            responseErrorMessage = json.getString(RestApi.RESPONSE_HEADER_PARAM_ERROR_MESSAGE_JSON_MESSAGE);
            if (responseErrorCode == null && responseErrorMessage == null) {
                return null;
            }
            if (responseErrorCode != null && responseErrorCode.length() > 0) {
                sb.append(responseErrorCode);
                sb.append(" : ");
            }
            if (responseErrorMessage != null && responseErrorMessage.length() > 0) {
                sb.append(responseErrorMessage);
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        if (sb.toString().length() > 5) {
            return sb.toString();
        }
        return null;
    }
}
