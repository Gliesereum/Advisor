package com.gliesereum.advisorapp.network.json.artbond;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class PaymentCalendarItem {

    @SerializedName("dividendValue")
    private double dividendValue;

    @SerializedName("date")
    private long date;

    @SerializedName("artBond")
    private Object artBond;

    @SerializedName("dividendPercent")
    private int dividendPercent;

    public void setDividendValue(double dividendValue) {
        this.dividendValue = dividendValue;
    }

    public double getDividendValue() {
        return dividendValue;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public void setArtBond(Object artBond) {
        this.artBond = artBond;
    }

    public Object getArtBond() {
        return artBond;
    }

    public void setDividendPercent(int dividendPercent) {
        this.dividendPercent = dividendPercent;
    }

    public int getDividendPercent() {
        return dividendPercent;
    }
}