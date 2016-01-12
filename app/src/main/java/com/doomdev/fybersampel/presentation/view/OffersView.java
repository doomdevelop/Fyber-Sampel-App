package com.doomdev.fybersampel.presentation.view;

import com.doomdev.fybersampel.presentation.model.OfferModel;

import java.util.List;

/**
 * Created by and on 08.01.16.
 */
public interface OffersView {
    void loadOffers(List<OfferModel> offerList);
    void hideProgress();
    void showProgress();
    void onError();
    void onNoInternetConnection();
}
