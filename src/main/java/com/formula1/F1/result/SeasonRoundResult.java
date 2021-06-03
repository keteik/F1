package com.formula1.F1.result;

public class SeasonRoundResult {
    private String position;
    private String name;
    private String surname;
    private String constructor;
    private String points;
    private String raceName;


    public void setPosition(String position) {
        this.position = position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setConstructor(String constructor) {
        this.constructor = constructor;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getConstructor() {
        return constructor;
    }

    public String getPoints() {
        return points;
    }

    public String getRaceName() {
        return raceName;
    }
}
