package com.example.hanium.classes;

import java.util.ArrayList;
import java.util.HashMap;

public class locationinfo {
    String count;
    ArrayList<SearchLocation> result;

    public locationinfo(String count, ArrayList<SearchLocation> result) {
        this.count = count;
        this.result = result;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public ArrayList<SearchLocation> getResult() {
        return result;
    }

    public void setResult(ArrayList<SearchLocation> result) {
        this.result = result;
    }
}
