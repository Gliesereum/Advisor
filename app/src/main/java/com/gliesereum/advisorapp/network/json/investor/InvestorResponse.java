package com.gliesereum.advisorapp.network.json.investor;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class InvestorResponse {

    @SerializedName("phone")
    private String phone;

    @SerializedName("operationsStories")
    private List<OperationsStoriesItem> operationsStories;

    @SerializedName("id")
    private String id;

    @SerializedName("user")
    private User user;

    @SerializedName("email")
    private String email;

    @SerializedName("paymentInfo")
    private PaymentInfo paymentInfo;

    @SerializedName("corporations")
    private List<CorporationsItem> corporations;

    @SerializedName("passedKycRequests")
    private List<PassedKycRequestsItem> passedKycRequests;

    @SerializedName("customer")
    private Customer customer;

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setOperationsStories(List<OperationsStoriesItem> operationsStories) {
        this.operationsStories = operationsStories;
    }

    public List<OperationsStoriesItem> getOperationsStories() {
        return operationsStories;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPaymentInfo(PaymentInfo paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public PaymentInfo getPaymentInfo() {
        return paymentInfo;
    }

    public void setCorporations(List<CorporationsItem> corporations) {
        this.corporations = corporations;
    }

    public List<CorporationsItem> getCorporations() {
        return corporations;
    }

    public void setPassedKycRequests(List<PassedKycRequestsItem> passedKycRequests) {
        this.passedKycRequests = passedKycRequests;
    }

    public List<PassedKycRequestsItem> getPassedKycRequests() {
        return passedKycRequests;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }
}