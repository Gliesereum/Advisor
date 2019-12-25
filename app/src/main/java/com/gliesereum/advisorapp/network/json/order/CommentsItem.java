package com.gliesereum.advisorapp.network.json.order;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class CommentsItem {

    @SerializedName("createBy")
    private CreateBy createBy;

    @SerializedName("stateType")
    private String stateType;

    @SerializedName("offerId")
    private String offerId;

    @SerializedName("create")
    private long create;

    @SerializedName("comment")
    private String comment;

    @SerializedName("createById")
    private String createById;

    @SerializedName("id")
    private String id;

    public void setCreateBy(CreateBy createBy) {
        this.createBy = createBy;
    }

    public CreateBy getCreateBy() {
        return createBy;
    }

    public void setStateType(String stateType) {
        this.stateType = stateType;
    }

    public String getStateType() {
        return stateType;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public String getOfferId() {
        return offerId;
    }

    public void setCreate(long create) {
        this.create = create;
    }

    public long getCreate() {
        return create;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setCreateById(String createById) {
        this.createById = createById;
    }

    public String getCreateById() {
        return createById;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}