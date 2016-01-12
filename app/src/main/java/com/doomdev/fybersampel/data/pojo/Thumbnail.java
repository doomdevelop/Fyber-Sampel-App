package com.doomdev.fybersampel.data.pojo;

/**
 * Created by and on 12.01.16.
 */
public class Thumbnail
{
    private String lowres;

    private String hires;

    public String getLowres ()
    {
        return lowres;
    }

    public void setLowres (String lowres)
    {
        this.lowres = lowres;
    }

    public String getHires ()
    {
        return hires;
    }

    public void setHires (String hires)
    {
        this.hires = hires;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [lowres = "+lowres+", hires = "+hires+"]";
    }
}

