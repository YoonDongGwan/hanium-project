package com.example.hanium.server;

import com.example.hanium.classes.ChatRoomInformation;

import java.util.ArrayList;

public class ChatRoomsResponse {
    private String success;
    private String message;
    private ArrayList<ChatRoomInformation> data;

    public ChatRoomsResponse(String success, String message, ArrayList<ChatRoomInformation> data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<ChatRoomInformation> getData() {
        return data;
    }

    public void setData(ArrayList<ChatRoomInformation> data) {
        this.data = data;
    }
}
