package com.doomdev.fybersampel.data.pojo;

/**
 * This model is main class representing fyber response and will be automatically created from json response
 * Created by and on 12.01.16.
 */
public class FyberResponse
{
    private String message;

    private Information information;

    private String count;

    private Offers[] offers;

    private String pages;

    private String code;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public Information getInformation ()
    {
        return information;
    }

    public void setInformation (Information information)
    {
        this.information = information;
    }

    public String getCount ()
    {
        return count;
    }

    public void setCount (String count)
    {
        this.count = count;
    }

    public Offers[] getOffers ()
    {
        return offers;
    }

    public void setOffers (Offers[] offers)
    {
        this.offers = offers;
    }

    public String getPages ()
    {
        return pages;
    }

    public void setPages (String pages)
    {
        this.pages = pages;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", information = "+information+", count = "+count+", offers = "+offers+", pages = "+pages+", code = "+code+"]";
    }
}
