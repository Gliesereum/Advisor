package com.gliesereum.advisorapp.network.json.order;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Customer {

    @SerializedName("userId")
    private String userId;

    @SerializedName("placeBirth")
    private String placeBirth;

    @SerializedName("customerType")
    private String customerType;

    @SerializedName("nationality")
    private String nationality;

    @SerializedName("passport")
    private String passport;

    @SerializedName("amountInvestment")
    private int amountInvestment;

    @SerializedName("borrowerType")
    private String borrowerType;

    @SerializedName("originFunds")
    private String originFunds;

    @SerializedName("name")
    private String name;

    @SerializedName("id")
    private String id;

    @SerializedName("position")
    private String position;

    @SerializedName("dateBirth")
    private long dateBirth;

    @SerializedName("investorType")
    private String investorType;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setPlaceBirth(String placeBirth) {
        this.placeBirth = placeBirth;
    }

    public String getPlaceBirth() {
        return placeBirth;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPassport() {
        return passport;
    }

    public void setAmountInvestment(int amountInvestment) {
        this.amountInvestment = amountInvestment;
    }

    public int getAmountInvestment() {
        return amountInvestment;
    }

    public void setBorrowerType(String borrowerType) {
        this.borrowerType = borrowerType;
    }

    public String getBorrowerType() {
        return borrowerType;
    }

    public void setOriginFunds(String originFunds) {
        this.originFunds = originFunds;
    }

    public String getOriginFunds() {
        return originFunds;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setDateBirth(long dateBirth) {
        this.dateBirth = dateBirth;
    }

    public long getDateBirth() {
        return dateBirth;
    }

    public void setInvestorType(String investorType) {
        this.investorType = investorType;
    }

    public String getInvestorType() {
        return investorType;
    }
}