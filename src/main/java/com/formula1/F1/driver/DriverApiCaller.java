package com.formula1.F1.driver;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DriverApiCaller {
    String driverId;

    DriverApiCaller(String driverId) { this.driverId = driverId; }

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
                throw  new RuntimeException("HttpResponseCode: " + status);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    void getDriverInformation(Driver driver) {
        String url = "http://ergast.com/api/f1/drivers/";
        url += driverId;
        url += ".json";

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
            jsonData = (JSONObject) jsonData.get("DriverTable");
            JSONArray driverData = (JSONArray) jsonData.get("Drivers");

            JSONObject info = (JSONObject) driverData.get(0);

            driver.setNumber(info.get("permanentNumber").toString());
            driver.setCode(info.get("code").toString());
            driver.setUrl(info.get("url").toString());
            driver.setName(info.get("givenName").toString());
            driver.setSurname(info.get("familyName").toString());
            driver.setDateOfBirth(info.get("dateOfBirth").toString());
            driver.setNationality(info.get("nationality").toString());

        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }
}
