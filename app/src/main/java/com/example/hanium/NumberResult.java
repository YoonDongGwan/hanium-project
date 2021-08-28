package com.example.hanium;

import com.google.gson.annotations.SerializedName;

public class NumberResult {
    @SerializedName("success")
    public String success;

    @SerializedName("message")
    public String message;

    @SerializedName("secretKey")
    public int secretKey;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String token) {
        this.secretKey = secretKey;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public NumberResult(String success, String message, int secretKey){
        this.success = success;
        this.message = message;
        this.secretKey = secretKey;
    }
}

