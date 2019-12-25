package com.gliesereum.advisorapp.network.json.investor;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class OperationsStoriesItem {

    @SerializedName("artBond")
    private ArtBond artBond;

    @SerializedName("operationStatus")
    private Object operationStatus;

    @SerializedName("documentUrl")
    private Object documentUrl;

    @SerializedName("description")
    private String description;

    @SerializedName("sum")
    private double sum;

    @SerializedName("daysAfterLastPayment")
    private Object daysAfterLastPayment;

    @SerializedName("artBondId")
    private String artBondId;

    @SerializedName("customerId")
    private String customerId;

    @SerializedName("name")
    private String name;

    @SerializedName("create")
    private long create;

    @SerializedName("comment")
    private Object comment;

    @SerializedName("operationType")
    private String operationType;

    @SerializedName("id")
    private String id;

    @SerializedName("stockCount")
    private int stockCount;

    public void setArtBond(ArtBond artBond) {
        this.artBond = artBond;
    }

    public ArtBond getArtBond() {
        return artBond;
    }

    public void setOperationStatus(Object operationStatus) {
        this.operationStatus = operationStatus;
    }

    public Object getOperationStatus() {
        return operationStatus;
    }

    public void setDocumentUrl(Object documentUrl) {
        this.documentUrl = documentUrl;
    }

    public Object getDocumentUrl() {
        return documentUrl;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getSum() {
        return sum;
    }

    public void setDaysAfterLastPayment(Object daysAfterLastPayment) {
        this.daysAfterLastPayment = daysAfterLastPayment;
    }

    public Object getDaysAfterLastPayment() {
        return daysAfterLastPayment;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCreate(long create) {
        this.create = create;
    }

    public long getCreate() {
        return create;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }

    public Object getComment() {
        return comment;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public int getStockCount() {
        return stockCount;
    }
}