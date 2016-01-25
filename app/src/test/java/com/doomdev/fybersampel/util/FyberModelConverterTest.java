package com.doomdev.fybersampel.util;

import com.doomdev.fybersampel.data.pojo.FyberResponse;
import com.doomdev.fybersampel.data.pojo.Offers;
import com.doomdev.fybersampel.presenter.model.OfferModel;
import com.doomdev.fybersampel.presenter.util.FyberModelConverter;
import com.doomdev.fybersampel.presenter.view.fragment.item.OfferItem;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Test for {@link FyberModelConverter}.class
 * Created by and on 22.01.16.
 */
public class FyberModelConverterTest {
    private FyberResponse fyberResponse;
    private FyberResponse emptyFyberresponse;
    private FyberModelConverter fyberModelConverter;
    private static final int SIZE = 2;
    private static final String TITLE = "title";

    @Before
    public void setUp() {
        fyberResponse = createFyberResponse();
        emptyFyberresponse = createEmptyFyberResponse();
        fyberModelConverter = new FyberModelConverter();

    }

    @Test
    public void testConvertFyberResponse() {
        List<OfferModel> offerModels = fyberModelConverter.convertFyberResponse(fyberResponse);
        Assert.assertNotNull(offerModels);
        Assert.assertTrue(offerModels.size() == SIZE);
        Assert.assertNull(offerModels.get(0).getPayout());
        Assert.assertNull(offerModels.get(0).getTeaser());
        Assert.assertNotNull(offerModels.get(0).getTitle());
        Assert.assertEquals(offerModels.get(0).getTitle(), TITLE);

    }

    @Test
    public void testemptyFyberResponse() {
        List<OfferModel> offerModels = fyberModelConverter.convertFyberResponse(emptyFyberresponse);
        Assert.assertNotNull(offerModels);
        Assert.assertTrue(offerModels.isEmpty());
    }

    @Test
    public void testConvertOfferModel() {
        List<OfferItem> offerModels = fyberModelConverter.convertOfferModel(fyberModelConverter.convertFyberResponse(fyberResponse));
        Assert.assertEquals(offerModels.size(), SIZE);
        Assert.assertEquals(offerModels.get(0).title, TITLE);
    }

    private FyberResponse createEmptyFyberResponse() {
        FyberResponse fyberResponse = new FyberResponse();
        fyberResponse.setOffers(createNullOffers());
        return fyberResponse;
    }

    private FyberResponse createFyberResponse() {
        FyberResponse fyberResponse = new FyberResponse();
        fyberResponse.setOffers(createOffers());
        return fyberResponse;
    }


    private Offers[] createOffers() {
        int count = SIZE;
        Offers[] offers = new Offers[SIZE];
        while (count > 0) {
            count--;
            offers[count] = createOffer();
        }
        return offers;
    }

    private Offers[] createNullOffers() {
        int count = SIZE;
        Offers[] offers = new Offers[SIZE];
        while (count > 0) {
            count--;
            offers[count] = null;
        }
        return offers;
    }

    private Offers createOffer() {
        Offers offers = new Offers();
        offers.setTitle(TITLE);
        return offers;
    }
}
