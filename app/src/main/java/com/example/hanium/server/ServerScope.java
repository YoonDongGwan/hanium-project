package com.example.hanium.server;

import com.example.hanium.classes.scope;
import com.google.gson.annotations.SerializedName;

public class ServerScope {
    @SerializedName("success")
    public String success;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public scope data;

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

    public scope getData() {
        return data;
    }

    public void setData(scope data) {
        this.data = data;
    }
    public ServerScope(String success, String message, scope data){
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
