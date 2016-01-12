package com.doomdev.fybersampel.data.pojo;

/**
 * Created by and on 12.01.16.
 */

public class Offer_types
{
    private String readable;

    private String offer_type_id;

    public String getReadable ()
    {
        return readable;
    }

    public void setReadable (String readable)
    {
        this.readable = readable;
    }

    public String getOffer_type_id ()
    {
        return offer_type_id;
    }

    public void setOffer_type_id (String offer_type_id)
    {
        this.offer_type_id = offer_type_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [readable = "+readable+", offer_type_id = "+offer_type_id+"]";
    }
}


