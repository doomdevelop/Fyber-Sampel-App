package com.doomdev.fybersampel.data.repository;

/**
 * Created by and on 12.01.16.
 */
public class LoadAdvertisingIdentifierRepository {
    private static LoadAdvertisingIdentifierRepository ourInstance = new LoadAdvertisingIdentifierRepository();

    public static LoadAdvertisingIdentifierRepository getInstance() {
        return ourInstance;
    }

    private LoadAdvertisingIdentifierRepository() {

    }
}
