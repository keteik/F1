package com.formula1.F1.result;

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

public class SeasonRoundResultApiCaller {
    private String season;
    private String round;
    private ArrayList<SeasonRoundResult> seasonRoundResultList = new ArrayList<>();
    private SeasonRoundResult seasonRoundResult;

    SeasonRoundResultApiCaller(String season, String round){
        this.season = season;
        this.round = round;
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

    void getSeasonRoundResult(Result result){

        if(season == ""){
            season = "none";
        }

        if(round == ""){
            round = "none";
        }

        String url = "http://ergast.com/api/f1/";
        url += season;
        url += "/";
        url += round;
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
            JSONArray seasonRoundData = (JSONArray) jsonData.get("Races");

            JSONObject info = (JSONObject) seasonRoundData.get(0);

            result.setRaceName(info.get("raceName").toString());

            seasonRoundData = (JSONArray) info.get("Results");


            for (Object i : seasonRoundData) {
                 info = (JSONObject) i;

                seasonRoundResult = new SeasonRoundResult();

                seasonRoundResult.setPosition(info.get("position").toString());
                seasonRoundResult.setPoints(info.get("points").toString());

                JSONObject driver = (JSONObject) info.get("Driver");
                seasonRoundResult.setName(driver.get("givenName").toString());
                seasonRoundResult.setSurname(driver.get("familyName").toString());

                JSONObject constructor = (JSONObject) info.get("Constructor");
                seasonRoundResult.setConstructor(constructor.get("constructorId").toString());

                seasonRoundResultList.add(seasonRoundResult);

            }
            result.setSeasonRoundResult(seasonRoundResultList);

        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }
}
