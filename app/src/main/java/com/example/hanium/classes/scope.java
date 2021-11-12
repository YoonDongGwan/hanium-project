package com.example.hanium.classes;

import java.util.ArrayList;

public class scope {
    private int count;
    private ArrayList<String> neighborhoods;

    public scope(int count, ArrayList<String> neighborhoods) {
        this.count = count;
        this.neighborhoods = neighborhoods;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<String> getNeighborhoods() {
        return neighborhoods;
    }

    public void setNeighborhoods(ArrayList<String> neighborhoods) {
        this.neighborhoods = neighborhoods;
    }
}

