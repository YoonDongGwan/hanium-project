package com.example.hanium.classes;

public class ChatRoomInformation {
    private int id;
    private String lastChat;
    private String lastChatDate;
    private String postTitle;
    private int sellerId;
    private int setterId;
    private int partner;

    public ChatRoomInformation(int id, String lastChat, String lastChatDate, String postTitle, int sellerId, int setterId, int partner) {
        this.id = id;
        this.lastChat = lastChat;
        this.lastChatDate = lastChatDate;
        this.postTitle = postTitle;
        this.sellerId = sellerId;
        this.setterId = setterId;
        this.partner = partner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastChat() {
        return lastChat;
    }

    public void setLastChat(String lastChat) {
        this.lastChat = lastChat;
    }

    public String getLastChatDate() {
        return lastChatDate;
    }

    public void setLastChatDate(String lastChatDate) {
        this.lastChatDate = lastChatDate;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getSetterId() {
        return setterId;
    }

    public void setSetterId(int setterId) {
        this.setterId = setterId;
    }

    public int getPartner() {
        return partner;
    }

    public void setPartner(int partner) {
        this.partner = partner;
    }
}
