package com.example.hanium.server;

import com.example.hanium.classes.SearchLocation;
import com.example.hanium.classes.locationinfo;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class LocationinfoResult {
    @SerializedName("success")
    public String success;
    @SerializedName("message")
    public String message;
    @SerializedName("data")
    public locationinfo data;

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

    public locationinfo getData() {
        return data;
    }

    public void setData(locationinfo data) {
        this.data = data;
    }
    public LocationinfoResult(String success, String message, locationinfo data){
        this.success = success;
        this.message = message;
        this.data = data;
    }
}


