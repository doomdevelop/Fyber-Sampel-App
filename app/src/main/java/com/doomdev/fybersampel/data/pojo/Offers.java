package com.doomdev.fybersampel.data.pojo;

/**
 * Created by and on 12.01.16.
 */


public class Offers
{
    private String title;

    private Thumbnail thumbnail;

    private String offer_id;

    private Time_to_payout time_to_payout;

    private String link;

    private String required_actions;

    private String teaser;

    private Offer_types[] offer_types;

    private String payout;

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public Thumbnail getThumbnail ()
    {
        return thumbnail;
    }

    public void setThumbnail (Thumbnail thumbnail)
    {
        this.thumbnail = thumbnail;
    }

    public String getOffer_id ()
    {
        return offer_id;
    }

    public void setOffer_id (String offer_id)
    {
        this.offer_id = offer_id;
    }

    public Time_to_payout getTime_to_payout ()
    {
        return time_to_payout;
    }

    public void setTime_to_payout (Time_to_payout time_to_payout)
    {
        this.time_to_payout = time_to_payout;
    }

    public String getLink ()
    {
        return link;
    }

    public void setLink (String link)
    {
        this.link = link;
    }

    public String getRequired_actions ()
    {
        return required_actions;
    }

    public void setRequired_actions (String required_actions)
    {
        this.required_actions = required_actions;
    }

    public String getTeaser ()
    {
        return teaser;
    }

    public void setTeaser (String teaser)
    {
        this.teaser = teaser;
    }

    public Offer_types[] getOffer_types ()
    {
        return offer_types;
    }

    public void setOffer_types (Offer_types[] offer_types)
    {
        this.offer_types = offer_types;
    }

    public String getPayout ()
    {
        return payout;
    }

    public void setPayout (String payout)
    {
        this.payout = payout;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [title = "+title+", thumbnail = "+thumbnail+", offer_id = "+offer_id+", time_to_payout = "+time_to_payout+", link = "+link+", required_actions = "+required_actions+", teaser = "+teaser+", offer_types = "+offer_types+", payout = "+payout+"]";
    }
}



