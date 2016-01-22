package com.doomdev.fybersampel.presenter.util;

import android.support.annotation.NonNull;

import com.doomdev.fybersampel.data.pojo.FyberResponse;
import com.doomdev.fybersampel.data.pojo.Offers;
import com.doomdev.fybersampel.data.pojo.Thumbnail;
import com.doomdev.fybersampel.presenter.model.OfferModel;
import com.doomdev.fybersampel.presenter.view.fragment.item.OfferItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Class contain methods converting different typs
 * Created by and on 18.01.16.
 */
public class FyberModelConverter {

    /**
     * Create from each offer stored in {@link FyberResponse} an {@link OfferModel}
     *
     * @param fyberResponse parse from giving {@link FyberResponse} only Offers with specific fields and store in  {@link OfferModel}
     * @return List with the offers represent by {@link OfferModel}
     */
    public List<OfferModel> convertFyberResponse(@NonNull FyberResponse fyberResponse) {
        List<OfferModel> offerModelsList = new ArrayList<>();
        Offers[] offers = fyberResponse.getOffers();
        for (Offers offer : offers) {
            if (offer != null) {
                offerModelsList.add(createOfferModel(offer));
            }
        }
        return offerModelsList;
    }

    private OfferModel createOfferModel(Offers offers) {
        OfferModel offerModel = new OfferModel();
        offerModel.setPayout(offers.getPayout());
        offerModel.setTeaser(offers.getTeaser());
        Thumbnail thumbnai = offers.getThumbnail();
        if (thumbnai != null) {
            offerModel.setThumbnail(offers.getThumbnail().getHires());
        }
        offerModel.setTitle(offers.getTitle());
        return offerModel;
    }

    /**
     * Convert each giving {@link OfferModel} in to {@link OfferItem}
     *
     * @param offerModelList list with content from type {@link OfferModel}
     * @return {@link OfferItem}
     */
    public List<OfferItem> convertOfferModel(@NonNull List<OfferModel> offerModelList) {

        List<OfferItem> offerItemList = new ArrayList<>();

        for (OfferModel offerModel : offerModelList) {
            offerItemList.add(createOfferItem(offerModel));
        }
        return offerItemList;
    }

    private OfferItem createOfferItem(OfferModel offerModel) {
        return new OfferItem(offerModel.getTitle(), offerModel.getTeaser(), offerModel.getThumbnail(), offerModel.getPayout());
    }
}
