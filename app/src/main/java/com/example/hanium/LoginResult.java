package com.example.hanium;

import com.google.gson.annotations.SerializedName;

public class LoginResult {
    @SerializedName("success")
    public String success;

    @SerializedName("message")
    public String message;

    @SerializedName("token")
    public String token;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public LoginResult(String success, String message, String token){
        this.success = success;
        this.message = message;
        this.token = token;
    }
}
