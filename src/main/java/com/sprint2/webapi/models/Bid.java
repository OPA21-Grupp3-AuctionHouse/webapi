package com.sprint2.webapi.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotNull;

@Document(collection = "bids")
public class Bid {

    @Id
    private String id;
    private String userId;
    private String auctionId;

    private String bidTime;
    @NotNull
    private double bidAmount;

    public Bid(String userId, String auctionId, String bidTime, double bidAmount) {
        this.userId = userId;
        this.auctionId = auctionId;
        this.bidTime = bidTime;
        this.bidAmount = bidAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public String getBidTime() {
        return bidTime;
    }

    public void setBidTime(String bidTime) {
        this.bidTime = bidTime;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }
}
