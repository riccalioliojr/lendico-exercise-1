package me.riccalioliojr.apps.model;

public class Request {
    private double loanAmount;
    private double nominalRate;
    private int duration;
    private String startDate;

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getNominalRate() {
        return nominalRate;
    }

    public int getDuration() {
        return duration;
    }

    public String getStartDate() {
        return startDate;
    }
}
