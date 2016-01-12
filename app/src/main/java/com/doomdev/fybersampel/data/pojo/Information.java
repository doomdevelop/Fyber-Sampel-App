package com.doomdev.fybersampel.data.pojo;

/**
 * Created by and on 12.01.16.
 */
public class Information {
    private String support_url;

    private String appid;

    private String virtual_currency;

    private String language;

    private String app_name;

    private String country;

    public String getSupport_url() {
        return support_url;
    }

    public void setSupport_url(String support_url) {
        this.support_url = support_url;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getVirtual_currency() {
        return virtual_currency;
    }

    public void setVirtual_currency(String virtual_currency) {
        this.virtual_currency = virtual_currency;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "ClassPojo [support_url = " + support_url + ", appid = " + appid + ", virtual_currency = " + virtual_currency + ", language = " + language + ", app_name = " + app_name + ", country = " + country + "]";
    }
}