package com.doomdev.fybersampel.data.service.rx;


import com.doomdev.fybersampel.data.pojo.FyberResponse;
import com.doomdev.fybersampel.presentation.model.OfferModel;

import java.util.List;
import java.util.Map;


import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import rx.Observable;

/**
 * format: json
 * appid: Application ID, provided as simple data "
 * uid: User ID, provided as simple data "
 * device_id: use Android advertising identifier !
 * locale: provided as simple data
 * ip: provided as simple data
 * offer_types: 112
 * Created by and on 10.01.16.
 * http://api.fyber.com/feed/v1/offers.json?appid=[APP_ID]&uid=[USER_ID]&ip=[IP_ADDRESS]&locale=[LOCALE]&device_id=[DEVICE_ID]&ps_time=[TIMESTAMP]&pub0=[CUSTOM]&timestamp=[UNIX_TIMESTAMP]&offer_types=[OFFER_TYPES]&google_ad_id=[GAID]&google_ad_id_limited_tracking_enabled=[GAID ENABLED]&hashkey=[HASHKEY]
 */
public interface FyberConnectionServiceRx {
    @GET("offers.json")
    Observable<OfferModel> connect(@Query("appid") String appid, @Query("uid") String uid, @Query("ip") String ip, @Query("locale") String locale,@Query("device_id") String device_id, @Query("offer_types") String offer_types);
    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("offers.json")
    Observable<FyberResponse> connect(@QueryMap Map<String, String> options);

}
