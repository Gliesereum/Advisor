package com.gliesereum.advisorapp.network.json.order;

import com.google.gson.annotations.SerializedName;

public class ArtBondOrdersSearchBody {

    @SerializedName("from")
    private Long from;

    @SerializedName("to")
    private Long to;

    @SerializedName("artBondId")
    private String artBondId;

    @SerializedName("state")
    private OfferStateType state;

    public Long getFrom() {
        return from;
    }

    public void setFrom(Long from) {
        this.from = from;
    }

    public Long getTo() {
        return to;
    }

    public void setTo(Long to) {
        this.to = to;
    }

    public String getArtBondId() {
        return artBondId;
    }

    public void setArtBondId(String artBondId) {
        this.artBondId = artBondId;
    }

    public OfferStateType getState() {
        return state;
    }

    public void setState(OfferStateType state) {
        this.state = state;
    }
}
