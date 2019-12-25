package com.gliesereum.advisorapp.network.json.investor;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class PassedKycRequestsItem {

    @SerializedName("updateDate")
    private long updateDate;

    @SerializedName("kycStatus")
    private String kycStatus;

    @SerializedName("comment")
    private Object comment;

    @SerializedName("id")
    private String id;

    @SerializedName("fields")
    private List<Object> fields;

    @SerializedName("objectId")
    private String objectId;

    @SerializedName("kycRequestType")
    private String kycRequestType;

    @SerializedName("createDate")
    private long createDate;

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setKycStatus(String kycStatus) {
        this.kycStatus = kycStatus;
    }

    public String getKycStatus() {
        return kycStatus;
    }

    public void setComment(Object comment) {
        this.comment = comment;
    }

    public Object getComment() {
        return comment;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setFields(List<Object> fields) {
        this.fields = fields;
    }

    public List<Object> getFields() {
        return fields;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setKycRequestType(String kycRequestType) {
        this.kycRequestType = kycRequestType;
    }

    public String getKycRequestType() {
        return kycRequestType;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getCreateDate() {
        return createDate;
    }
}