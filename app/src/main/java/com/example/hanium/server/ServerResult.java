package com.example.hanium.server;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class ServerResult {
    @SerializedName("success")
    private String success;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private HashMap<String, String> data;

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

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }
    public ServerResult(String success, String message, HashMap<String, String> data){
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
