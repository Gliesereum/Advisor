package com.gliesereum.advisorapp.network.json.artbond;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class PercentPerYear {

    @SerializedName("min")
    private int min;

    @SerializedName("max")
    private int max;

    public void setMin(int min) {
        this.min = min;
    }

    public int getMin() {
        return min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return max;
    }
}