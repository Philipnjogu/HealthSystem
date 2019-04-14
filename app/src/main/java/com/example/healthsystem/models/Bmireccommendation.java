package com.example.healthsystem.models;

public class Bmireccommendation {
    private double bmilevel;
    private String bmirec;
    public Bmireccommendation() {
    }

    public Bmireccommendation(double bmilevel, String bmirec) {
        this.bmilevel = bmilevel;
        this.bmirec = bmirec;
    }

    public double getBplevel() {
        return bmilevel;
    }

    public String getBprec() {
        return bmirec;
    }

}
