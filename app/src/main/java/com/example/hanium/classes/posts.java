package com.example.hanium.classes;

import java.util.Date;

public class posts {
    private int id;
    private int buyerId;
    private String title;
    private String description;
    private String status;
    private int price;
    private String requiredTime;
    private Date deadline;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private int sellerId;
    private int sellingDistrict;
    public posts(int id,
            int buyerId,
            String title,
            String description,
            String status,
            int price,
            String requiredTime,
            Date deadline,
            Date createdAt,
            Date updatedAt,
            Date deletedAt,
            int sellerId,
            int sellingDistrict){
        this.id = id;
        this.buyerId = buyerId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.price = price;
        this.requiredTime = requiredTime;
        this.deadline = deadline;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
        this.sellerId = sellerId;
        this.sellingDistrict = sellingDistrict;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRequiredTime() {
        return requiredTime;
    }

    public void setRequiredTime(String requiredTime) {
        this.requiredTime = requiredTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getSellingDistrict() {
        return sellingDistrict;
    }

    public void setSellingDistrict(int sellingDistrict) {
        this.sellingDistrict = sellingDistrict;
    }
}
