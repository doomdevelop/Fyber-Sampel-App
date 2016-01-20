package com.doomdev.fybersampel.presentation.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by and on 08.01.16.
 * Class that represents a Offer in the presentation layer.
 */
public class OfferModel implements Parcelable {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.teaser);
        dest.writeString(this.thumbnail);
        dest.writeString(this.payout);
    }

    public OfferModel() {
    }

    protected OfferModel(Parcel in) {
        this.title = in.readString();
        this.teaser = in.readString();
        this.thumbnail = in.readString();
        this.payout = in.readString();
    }

    public static final Parcelable.Creator<OfferModel> CREATOR = new Parcelable.Creator<OfferModel>() {
        public OfferModel createFromParcel(Parcel source) {
            return new OfferModel(source);
        }

        public OfferModel[] newArray(int size) {
            return new OfferModel[size];
        }
    };
}
