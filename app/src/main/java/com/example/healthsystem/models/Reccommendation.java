package com.example.healthsystem.models;

import com.google.firebase.firestore.Exclude;

public class Reccommendation {

    @Exclude
    String id;

    private double bslevel;
    private String type;
    private String bsrec;

    public Reccommendation() {
    }

    public Reccommendation(double bslevel, String bsrec, String type) {
        this.bslevel = bslevel;
        this.bsrec = bsrec;
        this.type = type;
    }

    public double getBslevel() {
        return bslevel;
    }

    public String getBsrec() {
        return bsrec;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
