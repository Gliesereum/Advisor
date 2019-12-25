package com.gliesereum.advisorapp.network.json.artbond;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ArtBondResponse {

    @SerializedName("statusType")
    private String statusType;

    @SerializedName("documents")
    private List<DocumentsItem> documents;

    @SerializedName("amountCollected")
    private double amountCollected;

    @SerializedName("nkd")
    private double nkd;

    @SerializedName("origin")
    private String origin;

    @SerializedName("latitude")
    private Object latitude;

    @SerializedName("paymentFinishDate")
    private long paymentFinishDate;

    @SerializedName("description")
    private String description;

    @SerializedName("dividendPercent")
    private int dividendPercent;

    @SerializedName("expertise")
    private String expertise;

    @SerializedName("artBondInfo")
    private List<Object> artBondInfo;

    @SerializedName("stockPrice")
    private double stockPrice;

    @SerializedName("literature")
    private String literature;

    @SerializedName("rewardPercent")
    private int rewardPercent;

    @SerializedName("price")
    private int price;

    @SerializedName("baseDividend")
    private double baseDividend;

    @SerializedName("dated")
    private String dated;

    @SerializedName("percentPerYear")
    private PercentPerYear percentPerYear;

    @SerializedName("id")
    private String id;

    @SerializedName("state")
    private String state;

    @SerializedName("longitude")
    private Object longitude;

    @SerializedName("execution")
    private String execution;

    @SerializedName("images")
    private List<ImagesItem> images;

    @SerializedName("releaseDate")
    private long releaseDate;

    @SerializedName("author")
    private String author;

    @SerializedName("authorInfo")
    private List<Object> authorInfo;

    @SerializedName("myOffers")
    private List<Object> myOffers;

    @SerializedName("article")
    private String article;

    @SerializedName("tags")
    private List<Object> tags;

    @SerializedName("size")
    private String size;

    @SerializedName("blockchain")
    private Object blockchain;

    @SerializedName("exhibitions")
    private String exhibitions;

    @SerializedName("name")
    private String name;

    @SerializedName("paymentDuration")
    private int paymentDuration;

    @SerializedName("location")
    private String location;

    @SerializedName("paymentCalendar")
    private List<PaymentCalendarItem> paymentCalendar;

    @SerializedName("paymentPeriod")
    private int paymentPeriod;

    @SerializedName("interested")
    private List<Object> interested;

    @SerializedName("specialStatusType")
    private String specialStatusType;

    @SerializedName("paymentStartDate")
    private long paymentStartDate;

    @SerializedName("stockCount")
    private int stockCount;

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setDocuments(List<DocumentsItem> documents) {
        this.documents = documents;
    }

    public List<DocumentsItem> getDocuments() {
        return documents;
    }

    public void setAmountCollected(double amountCollected) {
        this.amountCollected = amountCollected;
    }

    public double getAmountCollected() {
        return amountCollected;
    }

    public void setNkd(double nkd) {
        this.nkd = nkd;
    }

    public double getNkd() {
        return nkd;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setPaymentFinishDate(long paymentFinishDate) {
        this.paymentFinishDate = paymentFinishDate;
    }

    public long getPaymentFinishDate() {
        return paymentFinishDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDividendPercent(int dividendPercent) {
        this.dividendPercent = dividendPercent;
    }

    public int getDividendPercent() {
        return dividendPercent;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setArtBondInfo(List<Object> artBondInfo) {
        this.artBondInfo = artBondInfo;
    }

    public List<Object> getArtBondInfo() {
        return artBondInfo;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public void setLiterature(String literature) {
        this.literature = literature;
    }

    public String getLiterature() {
        return literature;
    }

    public void setRewardPercent(int rewardPercent) {
        this.rewardPercent = rewardPercent;
    }

    public int getRewardPercent() {
        return rewardPercent;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setBaseDividend(double baseDividend) {
        this.baseDividend = baseDividend;
    }

    public double getBaseDividend() {
        return baseDividend;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public String getDated() {
        return dated;
    }

    public void setPercentPerYear(PercentPerYear percentPerYear) {
        this.percentPerYear = percentPerYear;
    }

    public PercentPerYear getPercentPerYear() {
        return percentPerYear;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

    public Object getLongitude() {
        return longitude;
    }

    public void setExecution(String execution) {
        this.execution = execution;
    }

    public String getExecution() {
        return execution;
    }

    public void setImages(List<ImagesItem> images) {
        this.images = images;
    }

    public List<ImagesItem> getImages() {
        return images;
    }

    public void setReleaseDate(long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public long getReleaseDate() {
        return releaseDate;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthorInfo(List<Object> authorInfo) {
        this.authorInfo = authorInfo;
    }

    public List<Object> getAuthorInfo() {
        return authorInfo;
    }

    public void setMyOffers(List<Object> myOffers) {
        this.myOffers = myOffers;
    }

    public List<Object> getMyOffers() {
        return myOffers;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getArticle() {
        return article;
    }

    public void setTags(List<Object> tags) {
        this.tags = tags;
    }

    public List<Object> getTags() {
        return tags;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setBlockchain(Object blockchain) {
        this.blockchain = blockchain;
    }

    public Object getBlockchain() {
        return blockchain;
    }

    public void setExhibitions(String exhibitions) {
        this.exhibitions = exhibitions;
    }

    public String getExhibitions() {
        return exhibitions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPaymentDuration(int paymentDuration) {
        this.paymentDuration = paymentDuration;
    }

    public int getPaymentDuration() {
        return paymentDuration;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setPaymentCalendar(List<PaymentCalendarItem> paymentCalendar) {
        this.paymentCalendar = paymentCalendar;
    }

    public List<PaymentCalendarItem> getPaymentCalendar() {
        return paymentCalendar;
    }

    public void setPaymentPeriod(int paymentPeriod) {
        this.paymentPeriod = paymentPeriod;
    }

    public int getPaymentPeriod() {
        return paymentPeriod;
    }

    public void setInterested(List<Object> interested) {
        this.interested = interested;
    }

    public List<Object> getInterested() {
        return interested;
    }

    public void setSpecialStatusType(String specialStatusType) {
        this.specialStatusType = specialStatusType;
    }

    public String getSpecialStatusType() {
        return specialStatusType;
    }

    public void setPaymentStartDate(long paymentStartDate) {
        this.paymentStartDate = paymentStartDate;
    }

    public long getPaymentStartDate() {
        return paymentStartDate;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public int getStockCount() {
        return stockCount;
    }
}