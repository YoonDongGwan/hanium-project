package com.example.hanium.classes;

public class messageInfo {
    private int chatRoomId;
    private String content;

    public messageInfo(int chatRoomId, String content) {
        this.chatRoomId = chatRoomId;
        this.content = content;
    }

    public int getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(int chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
