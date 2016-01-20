package com.doomdev.fybersampel.presentation.util;

import com.doomdev.fybersampel.data.pojo.FyberResponse;
import com.doomdev.fybersampel.data.pojo.Offers;
import com.doomdev.fybersampel.presentation.model.OfferModel;
import com.doomdev.fybersampel.presentation.view.fragment.item.OfferItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by and on 18.01.16.
 */
public class FyberModelConverter {

    public List<OfferModel> convertOffers(FyberResponse fyberResponse){
        if(fyberResponse == null) {
            return null;
        }
        List<OfferModel> offerModelsList = new ArrayList<>();
         Offers[] offers = fyberResponse.getOffers();
        for(Offers offer : offers){
            offerModelsList.add(createOfferModel(offer));
        }
        return offerModelsList;
    }
    private OfferModel createOfferModel(Offers offers){
        OfferModel offerModel = new OfferModel();
        offerModel.setPayout(offers.getPayout());
        offerModel.setTeaser(offers.getTeaser());
        offerModel.setThumbnail(offers.getThumbnail().getHires());
        offerModel.setTitle(offers.getTitle());
        return offerModel;
    }

    public  List<OfferItem> convertOfferModel(List<OfferModel> offerModelList){
        if(offerModelList == null){
            return null;
        }
        List<OfferItem> offerItemList = new ArrayList<>();

        for(OfferModel offerModel : offerModelList){
            offerItemList.add(createOfferItem(offerModel));
        }
        return offerItemList;
    }

    private  OfferItem createOfferItem(OfferModel offerModel){
        return new OfferItem(offerModel.getTitle(),offerModel.getTeaser(),offerModel.getThumbnail(),offerModel.getPayout());
    }
}
