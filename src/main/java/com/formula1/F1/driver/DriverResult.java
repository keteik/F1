package com.formula1.F1.driver;

public class DriverResult {
    private String raceName;
    private String date;
    private String raceTime;
    private String number;
    private String position;
    private String points;

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRaceTime(String raceTime) {
        this.raceTime = raceTime;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getRaceName() {
        return raceName;
    }

    public String getDate() {
        return date;
    }

    public String getRaceTime() {
        return raceTime;
    }

    public String getNumber() {
        return number;
    }

    public String getPosition() {
        return position;
    }

    public String getPoints() {
        return points;
    }
}
