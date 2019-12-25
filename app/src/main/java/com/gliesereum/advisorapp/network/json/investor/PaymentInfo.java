package com.gliesereum.advisorapp.network.json.investor;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class PaymentInfo {

    @SerializedName("balance")
    private double balance;

    @SerializedName("nkd")
    private double nkd;

    @SerializedName("resultProfit")
    private double resultProfit;

    @SerializedName("profit")
    private double profit;

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setNkd(double nkd) {
        this.nkd = nkd;
    }

    public double getNkd() {
        return nkd;
    }

    public void setResultProfit(double resultProfit) {
        this.resultProfit = resultProfit;
    }

    public double getResultProfit() {
        return resultProfit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getProfit() {
        return profit;
    }
}