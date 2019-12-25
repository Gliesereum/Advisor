package com.gliesereum.advisorapp.network.json.artbond;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class InterestedItem {

    @SerializedName("artBondId")
    private String artBondId;

    @SerializedName("customerId")
    private String customerId;

    @SerializedName("id")
    private String id;

    public void setArtBondId(String artBondId) {
        this.artBondId = artBondId;
    }

    public String getArtBondId() {
        return artBondId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}