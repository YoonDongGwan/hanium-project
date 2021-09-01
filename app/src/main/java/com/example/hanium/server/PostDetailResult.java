package com.example.hanium.server;

import java.util.ArrayList;
import java.util.HashMap;
import com.example.hanium.classes.postDetail;

public class PostDetailResult {
    private String success;
    private String message;
    private postDetail data;

    public PostDetailResult(String success, String message, postDetail data) {
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

    public postDetail getData() {
        return data;
    }

    public void setData(postDetail data) {
        this.data = data;
    }
}
