package com.example.hanium.classes;

import java.util.ArrayList;

public class logtype {
    private ArrayList<posts> buyerUsage;
    private ArrayList<posts> sellerProceed;
    private ArrayList<posts> sellerEnd;

    public logtype(ArrayList<posts> buyerUsage, ArrayList<posts> sellerProceed, ArrayList<posts> sellerEnd) {
        this.buyerUsage = buyerUsage;
        this.sellerProceed = sellerProceed;
        this.sellerEnd = sellerEnd;
    }

    public ArrayList<posts> getBuyerUsage() {
        return buyerUsage;
    }

    public void setBuyerUsage(ArrayList<posts> buyerUsage) {
        this.buyerUsage = buyerUsage;
    }

    public ArrayList<posts> getSellerProceed() {
        return sellerProceed;
    }

    public void setSellerProceed(ArrayList<posts> sellerProceed) {
        this.sellerProceed = sellerProceed;
    }

    public ArrayList<posts> getSellerEnd() {
        return sellerEnd;
    }

    public void setSellerEnd(ArrayList<posts> sellerEnd) {
        this.sellerEnd = sellerEnd;
    }
}
