package com.doomdev.fybersampel.data.service.android;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

import java.io.IOException;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by and on 12.01.16.
 */
public class LoadAdvertisingIdentifierService {
    private Context context;
    public LoadAdvertisingIdentifierService(Context context){
        this.context = context;
    }

    public Observable<String> load(){
        return getAdvertisingIdInfo();
    }

    private Observable<String> getAdvertisingIdInfo() {
        return Observable.create(new  Observable. OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                   String devId = AdvertisingIdClient.getAdvertisingIdInfo(context).getId();
                    subscriber.onNext(devId);
                } catch (IOException e) {
                    subscriber.onError(e);
                } catch (GooglePlayServicesNotAvailableException e) {
                    subscriber.onError(e);
                } catch (GooglePlayServicesRepairableException e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
