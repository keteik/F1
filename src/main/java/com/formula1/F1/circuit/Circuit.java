package com.formula1.F1.circuit;

import java.util.ArrayList;

public class Circuit {
    private String season;
    private ArrayList<CircuitSeason> circuitSeason = new ArrayList<CircuitSeason>();

    public void setSeason(String season) {
        this.season = season;
    }

    public void setCircuitSeason(ArrayList<CircuitSeason> circuitSeason) {
        this.circuitSeason = circuitSeason;
    }

    public String getSeason() {
        return season;
    }

    public ArrayList<CircuitSeason> getCircuitSeason() {
        return circuitSeason;
    }
}
