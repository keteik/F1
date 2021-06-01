package com.formula1.F1.driver;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class DriverResultApiCaller {
    private String driverId;
    private String season;
    private DriverResult driverResult;
    private ArrayList<DriverResult> driverResultList = new ArrayList<>();


    DriverResultApiCaller(String driverId, String season){
        this.driverId = driverId;
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

    void getDriverSeason(Driver driver) {
        if(driverId == ""){
            driverId = "none";
        }
        try{
            Integer a = Integer.parseInt(season);
        }catch (NumberFormatException e){
            season = "2050";
        }

        String url = "http://ergast.com/api/f1/";
        url += season;
        url += "/drivers/";
        url += driverId;
        url += "/results";
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
            JSONArray raceData = (JSONArray) jsonData.get("Races");


            for(int i = 0; i < raceData.size(); i++){
                driverResult = new DriverResult();

                JSONObject info = (JSONObject) raceData.get(i);

                driverResult.setRaceName(info.get("raceName").toString());
                driverResult.setDate(info.get("date").toString());
                driverResult.setRaceTime(info.get("time").toString());

                JSONArray raceResult = (JSONArray) info.get("Results");
                JSONObject raceInfo = (JSONObject) raceResult.get(0);

                driverResult.setNumber(raceInfo.get("number").toString());
                driverResult.setPosition(raceInfo.get("position").toString());
                driverResult.setPoints(raceInfo.get("points").toString());

                driverResultList.add(driverResult);
            }
            driver.setDriverResult(driverResultList);

        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }
}
