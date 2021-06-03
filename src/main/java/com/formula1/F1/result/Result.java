package com.formula1.F1.result;

import java.util.ArrayList;

public class Result {
    private String season;
    private String round;
    private String raceName;
    private ArrayList<SeasonRoundResult> seasonRoundResult = new ArrayList<SeasonRoundResult>();

    public void setSeason(String season) {
        this.season = season;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public void setSeasonRoundResult(ArrayList<SeasonRoundResult> seasonRoundResult) {
        this.seasonRoundResult = seasonRoundResult;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getSeason() {
        return season;
    }

    public String getRound() {
        return round;
    }

    public ArrayList<SeasonRoundResult> getSeasonRoundResult() {
        return seasonRoundResult;
    }

    public String getRaceName() {
        return raceName;
    }
}
