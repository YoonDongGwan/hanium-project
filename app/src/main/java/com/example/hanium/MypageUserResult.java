package com.example.hanium;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class MypageUserResult {
    @SerializedName("success")
    public String success;
    @SerializedName("message")
    public String message;
    @SerializedName("mypage")
    public HashMap<String, String> mypage;

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

    public HashMap<String, String> getMypage() {
        return mypage;
    }

    public void setMypage(HashMap<String, String> mypage) {
        this.mypage = mypage;
    }
    public MypageUserResult(String success, String message, HashMap<String, String> mypage){
        this.success = success;
        this.message = message;
        this.mypage = mypage;
    }
}
