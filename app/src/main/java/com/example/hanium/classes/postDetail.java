package com.example.hanium.classes;

public class postDetail {
    private postInfo postInfo;
    private sellerInfo sellerInfo;

    public postDetail(postInfo postInfo, sellerInfo sellerInfo) {
        this.postInfo = postInfo;
        this.sellerInfo = sellerInfo;
    }

    public postInfo getPostInfo() {
        return postInfo;
    }

    public void setPostInfo(postInfo postInfo) {
        this.postInfo = postInfo;
    }

    public sellerInfo getSellerInfo() {
        return sellerInfo;
    }

    public void setSellerInfo(sellerInfo sellerInfo) {
        this.sellerInfo = sellerInfo;
    }
}
