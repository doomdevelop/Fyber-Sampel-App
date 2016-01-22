package com.doomdev.fybersampel.presenter.view;

/**
 * Types of Handle messages
 * Created by and on 08.01.16.
 */
public interface Msg {

    int ON_OFFERS_LOADED = 1;
    int ON_ADVERTISING_IDENTIFIER_LOADED = 2;
    int HIDE_PROGRESS = 3;
    int SHOW_PROGRESS = 4;
    int ON_ERROR = 5;
    int ON_NO_INTERNET_CONNECTION = 6;
    int ON_EMPTY_OFFERS_LOADED = 7;
}
