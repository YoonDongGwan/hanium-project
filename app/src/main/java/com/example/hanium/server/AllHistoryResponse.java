package com.example.hanium.server;

import com.example.hanium.classes.logtype;

public class AllHistoryResponse {
    private String success;
    private String message;
    private logtype data;

    public AllHistoryResponse(String success, String message, logtype data) {
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

    public logtype getData() {
        return data;
    }

    public void setData(logtype data) {
        this.data = data;
    }
}
