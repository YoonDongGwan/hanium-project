package com.example.hanium.classes;

import java.util.ArrayList;
import java.util.Date;

public class postInfo {
    private int id;
    private String title;
    private String description;
    private String status;
    private int price;
    private String requiredTime;
    private Date deadline;
    private Date createdAt;
    private Date updatedAt;
    private String sellingDistrict;
    private ArrayList<String> postImages;

    public postInfo(int id, String title, String description, String status, int price, String requiredTime, Date deadline, Date createdAt, Date updatedAt, String sellingDistrict, ArrayList<String> postImages) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.price = price;
        this.requiredTime = requiredTime;
        this.deadline = deadline;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.sellingDistrict = sellingDistrict;
        this.postImages = postImages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setCreatedAt(Date createAt) {
        this.createdAt = createAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updateAt) {
        this.updatedAt = updateAt;
    }

    public String getSellingDistrict() {
        return sellingDistrict;
    }

    public void setSellingDistrict(String sellingDistrict) {
        this.sellingDistrict = sellingDistrict;
    }

    public ArrayList<String> getPostImages() {
        return postImages;
    }

    public void setPostImages(ArrayList<String> postImages) {
        this.postImages = postImages;
    }
}
