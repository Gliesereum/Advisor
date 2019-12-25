package com.gliesereum.advisorapp.network.json.investor;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class CorporationsItem {

    @SerializedName("businessActivity")
    private String businessActivity;

    @SerializedName("country")
    private String country;

    @SerializedName("placeIncorporation")
    private Object placeIncorporation;

    @SerializedName("updateDate")
    private long updateDate;

    @SerializedName("city")
    private String city;

    @SerializedName("corporationEmployees")
    private List<Object> corporationEmployees;

    @SerializedName("officeNumber")
    private String officeNumber;

    @SerializedName("corporationSharedOwnerships")
    private List<CorporationSharedOwnershipsItem> corporationSharedOwnerships;

    @SerializedName("description")
    private String description;

    @SerializedName("kycApproved")
    private boolean kycApproved;

    @SerializedName("street")
    private String street;

    @SerializedName("rcNumber")
    private Object rcNumber;

    @SerializedName("buildingNumber")
    private String buildingNumber;

    @SerializedName("id")
    private String id;

    @SerializedName("dateIncorporation")
    private Object dateIncorporation;

    @SerializedName("createDate")
    private long createDate;

    @SerializedName("deleteDate")
    private Object deleteDate;

    @SerializedName("objectState")
    private String objectState;

    @SerializedName("companyType")
    private Object companyType;

    @SerializedName("index")
    private String index;

    @SerializedName("logoUrl")
    private String logoUrl;

    @SerializedName("coverUrl")
    private String coverUrl;

    @SerializedName("phone")
    private Object phone;

    @SerializedName("name")
    private String name;

    @SerializedName("region")
    private String region;

    public void setBusinessActivity(String businessActivity) {
        this.businessActivity = businessActivity;
    }

    public String getBusinessActivity() {
        return businessActivity;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setPlaceIncorporation(Object placeIncorporation) {
        this.placeIncorporation = placeIncorporation;
    }

    public Object getPlaceIncorporation() {
        return placeIncorporation;
    }

    public void setUpdateDate(long updateDate) {
        this.updateDate = updateDate;
    }

    public long getUpdateDate() {
        return updateDate;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCorporationEmployees(List<Object> corporationEmployees) {
        this.corporationEmployees = corporationEmployees;
    }

    public List<Object> getCorporationEmployees() {
        return corporationEmployees;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setCorporationSharedOwnerships(List<CorporationSharedOwnershipsItem> corporationSharedOwnerships) {
        this.corporationSharedOwnerships = corporationSharedOwnerships;
    }

    public List<CorporationSharedOwnershipsItem> getCorporationSharedOwnerships() {
        return corporationSharedOwnerships;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setKycApproved(boolean kycApproved) {
        this.kycApproved = kycApproved;
    }

    public boolean isKycApproved() {
        return kycApproved;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setRcNumber(Object rcNumber) {
        this.rcNumber = rcNumber;
    }

    public Object getRcNumber() {
        return rcNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setDateIncorporation(Object dateIncorporation) {
        this.dateIncorporation = dateIncorporation;
    }

    public Object getDateIncorporation() {
        return dateIncorporation;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setDeleteDate(Object deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Object getDeleteDate() {
        return deleteDate;
    }

    public void setObjectState(String objectState) {
        this.objectState = objectState;
    }

    public String getObjectState() {
        return objectState;
    }

    public void setCompanyType(Object companyType) {
        this.companyType = companyType;
    }

    public Object getCompanyType() {
        return companyType;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIndex() {
        return index;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }
}