package com.formula1.F1.schedule;

import com.formula1.F1.constructor.Constructor;
import com.formula1.F1.constructor.ConstructorDriver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ScheduleApiCaller {
    private String season;
    private ArrayList<ScheduleSeason> scheduleSeasonsList = new ArrayList<>();
    private ScheduleSeason scheduleSeasons;

    public ScheduleApiCaller(String season) {
        this.season = season;
    }


    URL makeRequest(String target){
        URL url= null;

        try{
            url = new URL(target);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.connect();

            int status = conn.getResponseCode();

            if(status != 200){
                System.out.println("Code: " + status);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    void getSeasonSchedule(Schedule schedule){

        if(season == ""){
            season = "none";
        }

        String url = "http://ergast.com/api/f1/";
        url += season;
        url += ".json";
        url += "?limit=1000";

        try{
            Scanner sc = new Scanner(makeRequest(url).openStream());

            String content = "";
            while(sc.hasNext()){
                //content += sc.nextLine();
                content = content.concat(sc.nextLine());
            }
            sc.close();

            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(content);

            jsonData = (JSONObject) jsonData.get("MRData");
            jsonData = (JSONObject) jsonData.get("RaceTable");
            JSONArray scheduleSeasonData = (JSONArray) jsonData.get("Races");


            for (Object i : scheduleSeasonData) {
                JSONObject info = (JSONObject) i;
                scheduleSeasons = new ScheduleSeason();

                scheduleSeasons.setRound(info.get("round").toString());
                scheduleSeasons.setUrl(info.get("url").toString());
                scheduleSeasons.setRaceName(info.get("raceName").toString());

                JSONObject circuit = (JSONObject) info.get("Circuit");
                scheduleSeasons.setCircuitName(circuit.get("circuitName").toString());

                scheduleSeasonsList.add(scheduleSeasons);
            }
            schedule.setScheduleSeason(scheduleSeasonsList);

        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }
}
