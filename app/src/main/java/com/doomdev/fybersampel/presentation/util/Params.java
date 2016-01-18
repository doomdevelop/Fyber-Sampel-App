package com.doomdev.fybersampel.presentation.util;

/**
 * Created by and on 18.01.16.
 */
public enum Params {

    FORMAT("format", "json"),
    APPID("appid", "2070"),
    UID("uid", "spiderman"),
    LOCALE("locale", "de"),
    IP("ip", "109.235.143.113"),
    OFFER_TYPES("offer_types", "112"),
    TIMESTAMP("timestamp", ""),
    DEVICE_ID("device_id", "");

    private final String key;
    private final String value;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    private Params(String key, String value) {
        this.key = key;
        this.value = value;
    }

}
