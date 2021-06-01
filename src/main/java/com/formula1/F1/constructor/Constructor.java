package com.formula1.F1.constructor;

import java.util.ArrayList;

public class Constructor {
    private String id;
    private ArrayList<ConstructorDriver> constructorDriver = new ArrayList<>();

    public void setId(String id) {
        this.id = id;
    }

    public void setConstructorDriver(ArrayList<ConstructorDriver> constructorDriver) {
        this.constructorDriver = constructorDriver;
    }

    public String getId() {
        return id;
    }

    public ArrayList<ConstructorDriver> getConstructorDriver() {
        return constructorDriver;
    }
}
