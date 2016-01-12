package com.doomdev.fybersampel.presentation.model;

/**
 * Created by and on 08.01.16.
 * Class that represents a Offer in the presentation layer.
 */
public class OfferModel {
    private String title;
    private String teaser;
    private String thumbnail;
    private String payout;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setPayout(String payout) {
        this.payout = payout;
    }

    public String getTitle() {
        return title;
    }

    public String getTeaser() {
        return teaser;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getPayout() {
        return payout;
    }

}
