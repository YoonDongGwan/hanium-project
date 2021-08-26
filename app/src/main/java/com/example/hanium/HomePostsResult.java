package com.example.hanium;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HomePostsResult {
    @SerializedName("success")
    public String success;
    @SerializedName("message")
    public String message;
    @SerializedName("posts")
    public ArrayList<posts> posts;

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

    public ArrayList<posts> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<posts> posts) {
        this.posts = posts;
    }
    public HomePostsResult(String success, String message, ArrayList<posts> posts){
        this.success = success;
        this.message = message;
        this.posts = posts;
    }
}
