package com.example.hanium.server;

import com.example.hanium.classes.posts;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HomePostsResult {
    @SerializedName("success")
    public String success;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public ArrayList<posts> data;

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

    public ArrayList<posts> getData() {
        return data;
    }

    public void setData(ArrayList<posts> data) {
        this.data = data;
    }
    public HomePostsResult(String success, String message, ArrayList<posts> data){
        this.success = success;
        this.message = message;
        this.data = data;
    }
}

