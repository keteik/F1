package com.formula1.F1.schedule;

import java.util.ArrayList;

public class Schedule {
    private String id;
    private ArrayList<ScheduleSeason> scheduleSeason = new ArrayList<>();

    public void setId(String id) {
        this.id = id;
    }

    public void setScheduleSeason(ArrayList<ScheduleSeason> scheduleSeason) {
        this.scheduleSeason = scheduleSeason;
    }

    public String getId() {
        return id;
    }

    public ArrayList<ScheduleSeason> getScheduleSeason() {
        return scheduleSeason;
    }
}
