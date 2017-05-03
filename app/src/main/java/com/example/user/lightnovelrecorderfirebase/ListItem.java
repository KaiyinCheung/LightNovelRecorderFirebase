package com.example.user.lightnovelrecorderfirebase;

/**
 * Created by User on 25/3/2017.
 */

public class ListItem {
    private int id;
    private String name;
    private String progress;
    private String other;
    private String date;

    public void setOther(String other) {
        this.other = other;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOther() {
        return other;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}
