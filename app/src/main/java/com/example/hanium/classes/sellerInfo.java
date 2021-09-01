package com.example.hanium.classes;

import java.util.Date;

public class sellerInfo {
    private String nickname;
    private String mannerPoint;
    private Date createAt;
    private String profileImages;
    private String simpleAdrress;
    private int sellCount;
    private int buyCount;

    public sellerInfo(String nickname, String mannerPoint, Date createAt, String profileImages, String simpleAdrress, int sellCount, int buyCount) {
        this.nickname = nickname;
        this.mannerPoint = mannerPoint;
        this.createAt = createAt;
        this.profileImages = profileImages;
        this.simpleAdrress = simpleAdrress;
        this.sellCount = sellCount;
        this.buyCount = buyCount;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMannerPoint() {
        return mannerPoint;
    }

    public void setMannerPoint(String mannerPoint) {
        this.mannerPoint = mannerPoint;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getProfileImages() {
        return profileImages;
    }

    public void setProfileImages(String profileImages) {
        this.profileImages = profileImages;
    }

    public String getSimpleAdrress() {
        return simpleAdrress;
    }

    public void setSimpleAdrress(String simpleAdrress) {
        this.simpleAdrress = simpleAdrress;
    }

    public int getSellCount() {
        return sellCount;
    }

    public void setSellCount(int sellCount) {
        this.sellCount = sellCount;
    }

    public int getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(int buyCount) {
        this.buyCount = buyCount;
    }
}
