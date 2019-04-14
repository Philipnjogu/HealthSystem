package com.example.healthsystem.models;

public class Reccommendation {

    private double bslevel;
    private String bsrec;

    public Reccommendation() {
    }

    public Reccommendation(double bslevel, String bsrec) {
        this.bslevel = bslevel;
        this.bsrec = bsrec;
    }

    public double getBslevel() {
        return bslevel;
    }

    public String getBsrec() {
        return bsrec;
    }
}
