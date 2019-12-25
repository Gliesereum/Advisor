package com.gliesereum.advisorapp.network.json.order;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ArtBondOrderResponse {

    @SerializedName("artBond")
    private ArtBond artBond;

    @SerializedName("comments")
    private List<CommentsItem> comments;

    @SerializedName("sumInvestment")
    private int sumInvestment;

    @SerializedName("artBondId")
    private String artBondId;

    @SerializedName("stateType")
    private String stateType;

    @SerializedName("customerId")
    private String customerId;

    @SerializedName("create")
    private long create;

    @SerializedName("id")
    private String id;

    @SerializedName("user")
    private User user;

    @SerializedName("stockCount")
    private int stockCount;

    @SerializedName("customer")
    private Customer customer;

    public void setArtBond(ArtBond artBond) {
        this.artBond = artBond;
    }

    public ArtBond getArtBond() {
        return artBond;
    }

    public void setComments(List<CommentsItem> comments) {
        this.comments = comments;
    }

    public List<CommentsItem> getComments() {
        return comments;
    }

    public void setSumInvestment(int sumInvestment) {
        this.sumInvestment = sumInvestment;
    }

    public int getSumInvestment() {
        return sumInvestment;
    }

    public void setArtBondId(String artBondId) {
        this.artBondId = artBondId;
    }

    public String getArtBondId() {
        return artBondId;
    }

    public void setStateType(String stateType) {
        this.stateType = stateType;
    }

    public String getStateType() {
        return stateType;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCreate(long create) {
        this.create = create;
    }

    public long getCreate() {
        return create;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}