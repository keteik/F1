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

public class DriverConstructorApiCaller {
    private String driverId;

    DriverConstructorApiCaller(String driverId){
        this.driverId = driverId;
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
                throw  new RuntimeException("HttpResponseCode: " + status);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    void getDriverConstructors(Driver driver){


        String url = "http://ergast.com/api/f1/drivers/";
        url += driverId;
        url += "/constructors";
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
            jsonData = (JSONObject) jsonData.get("ConstructorTable");
            JSONArray driverConstructorData = (JSONArray) jsonData.get("Constructors");

            ArrayList<DriverConstructor> driverConstructorList = new ArrayList<>();
            DriverConstructor driverConstructor;


            for (Object i : driverConstructorData) {
                JSONObject info = (JSONObject) i;
                driverConstructor = new DriverConstructor();

                driverConstructor.setConstructorId(info.get("constructorId").toString());
                driverConstructor.setUrl(info.get("url").toString());
                driverConstructor.setName(info.get("name").toString());
                driverConstructor.setNationality(info.get("nationality").toString());

                driverConstructorList.add(driverConstructor);
            }

            driver.setDriverConstructor(driverConstructorList);

        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }
}
