package com.doomdev.fybersampel.data.pojo;

/**
 * Created by and on 12.01.16.
 */
public class Time_to_payout
{
    private String amount;

    private String readable;

    public String getAmount ()
    {
        return amount;
    }

    public void setAmount (String amount)
    {
        this.amount = amount;
    }

    public String getReadable ()
    {
        return readable;
    }

    public void setReadable (String readable)
    {
        this.readable = readable;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [amount = "+amount+", readable = "+readable+"]";
    }
}
