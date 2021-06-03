package com.formula1.F1.circuit;

import com.formula1.F1.schedule.Schedule;
import com.formula1.F1.schedule.ScheduleSeason;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class CircuitSeasonApiCaller {
    private String season;
    private ArrayList<CircuitSeason> circuitSeasonList = new ArrayList<>();
    private CircuitSeason circuitSeason;

    public CircuitSeasonApiCaller(String season) {
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

    void getSeasonCircuit(Circuit circuit){

        if(season == ""){
            season = "none";
        }

        String url = "http://ergast.com/api/f1/";
        url += season;
        url += "/circuits";
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
            jsonData = (JSONObject) jsonData.get("CircuitTable");
            System.out.println(jsonData);
            JSONArray circuitData = (JSONArray) jsonData.get("Circuits");


            for (Object i : circuitData) {
                JSONObject info = (JSONObject) i;

                circuitSeason = new CircuitSeason();

                circuitSeason.setUrl(info.get("url").toString());
                circuitSeason.setCircuitName(info.get("circuitName").toString());

                JSONObject location = (JSONObject) info.get("Location");
                circuitSeason.setLocality(location.get("locality").toString());
                circuitSeason.setCountry(location.get("country").toString());

                circuitSeasonList.add(circuitSeason);
            }
            circuit.setCircuitSeason(circuitSeasonList);

        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }
}
