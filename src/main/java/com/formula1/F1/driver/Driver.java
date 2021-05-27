package com.formula1.F1.driver;

import java.util.ArrayList;

public class Driver {

    private String id;
    private String season;
    private ArrayList<DriverConstructor> driverConstructor = new ArrayList<>();
    private DriverInformation driverInformation;
    private ArrayList<DriverResult> driverResult = new ArrayList<>();

    public void setId(String id) {
        this.id = id;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public void setDriverConstructor(ArrayList<DriverConstructor> driverConstructor) {
        this.driverConstructor = driverConstructor;
    }

    public void setDriverInformation(DriverInformation driverInformation) {
        this.driverInformation = driverInformation;
    }

    public void setDriverResult(ArrayList<DriverResult> driverResult) {
        this.driverResult = driverResult;
    }

    public String getId() {
        return id;
    }

    public String getSeason() {
        return season;
    }

    public ArrayList<DriverConstructor> getDriverConstructor() {

        return driverConstructor;
    }

    public DriverInformation getDriverInformation() {

        return driverInformation;
    }

    public ArrayList<DriverResult> getDriverResult() {
        return driverResult;
    }
}
