package com.example.healthsystem.models;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Notification implements Serializable {

    @Exclude
    private String id;

    private String type;
    private String desc;

    public Notification(){}

    public Notification(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
