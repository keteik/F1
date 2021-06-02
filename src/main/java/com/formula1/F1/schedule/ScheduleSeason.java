package com.formula1.F1.schedule;

public class ScheduleSeason {
    private String round;
    private String url;
    private String raceName;
    private String circuitName;


    public void setRound(String round) {
        this.round = round;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public void setCircuitName(String circuitName) {
        this.circuitName = circuitName;
    }

    public String getRound() {
        return round;
    }

    public String getUrl() {
        return url;
    }

    public String getRaceName() {
        return raceName;
    }

    public String getCircuitName() {
        return circuitName;
    }
}
