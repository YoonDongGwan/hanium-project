package com.example.hanium.classes;

public class ErrandPost {
    private String title;
    private String destination;
    private String deadline;
    private String time;
    private String state;
    public ErrandPost(String title,String destination, String deadline, String time,String state){
        this.title = title;
        this.destination = destination;
        this.deadline = deadline;
        this.time = time;
        this.state=state;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public String getDestination() {
        return destination;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getTime() {
        return time;
    }
    public String getState(){
        return state;
    }
}
