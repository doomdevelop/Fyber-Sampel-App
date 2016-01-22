package com.doomdev.fybersampel.presenter.view.fragment.item;

/**
 * The Item represent offer in the list view.
 * Created by and on 08.01.16.
 */
public class OfferItem {
    public final String title;
    public final String teaser;
    public final String thumbnail;
    public final String payout;

    public OfferItem(String title, String teaser, String thumbnail, String payout) {
        this.title = title;
        this.teaser = teaser;
        this.thumbnail = thumbnail;
        this.payout = payout;
    }
}
