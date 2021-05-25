package com.formula1.F1.driver;

import java.util.ArrayList;

public class Driver {

    private String id;
    private ArrayList<DriverConstructor> driverConstructor = new ArrayList<>();
    private DriverInformation driverInformation;

    public void setId(String id) {
        this.id = id;
    }

    public void setDriverConstructor(ArrayList<DriverConstructor> driverConstructor) {
        this.driverConstructor = driverConstructor;
    }

    public void setDriverInformation(DriverInformation driverInformation) {
        this.driverInformation = driverInformation;
    }

    public String getId() {
        return id;
    }

    public ArrayList<DriverConstructor> getDriverConstructor() {
        return driverConstructor;
    }

    public DriverInformation getDriverInformation() {
        return driverInformation;
    }
}
