package com.example.healthsystem.models;

public class Bpreccommendation {
    private double bplevel;
    private String bprec;
    public Bpreccommendation() {
    }

    public Bpreccommendation(double bplevel, String bprec) {
        this.bplevel = bplevel;
        this.bprec = bprec;
    }

    public double getBplevel() {
        return bplevel;
    }

    public String getBprec() {
        return bprec;
    }
}
