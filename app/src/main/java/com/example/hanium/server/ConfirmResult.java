package com.example.hanium.server;

import com.google.gson.annotations.SerializedName;

public class ConfirmResult {
    @SerializedName("success")
    public String success;

    @SerializedName("message")
    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public ConfirmResult(String success, String message){
        this.success = success;
        this.message = message;
    }
}


