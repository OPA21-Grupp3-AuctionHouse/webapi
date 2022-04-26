package com.sprint2.webapi.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "auctions")
public class Auction {

    @Id
    private String id;
    private String ownerId;


    private String imageURL;

    private String category;
    private String name;
    private String description;
    private double price;
    private double buyout;
    private String endTime;
    private String orderStatus;

    public Auction(String ownerId, String imageURL, String category, String name, String description, double price, double buyout, String endTime, String orderStatus) {

        this.ownerId = ownerId;
        this.imageURL = imageURL;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.buyout = buyout;
        this.endTime = endTime;
        this.orderStatus = orderStatus;
    }

    public String getId() {
        return id;
    }


    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL (String image) {
        this.imageURL = imageURL ;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getBuyout() {
        return buyout;
    }

    public void setBuyout(double buyout) {
        this.buyout = buyout;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
