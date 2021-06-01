package com.formula1.F1.constructor;

import com.formula1.F1.driver.Driver;
import com.formula1.F1.driver.DriverConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ConstructorDriverApiCaller {
    private String constructorId;
    private ArrayList<ConstructorDriver> constructorDriverList = new ArrayList<>();
    private ConstructorDriver constructorDriver;

    ConstructorDriverApiCaller(String driverId){
        this.constructorId = driverId;
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

    void getDriverConstructors(Constructor constructor){

        if(constructorId == ""){
            constructorId = "none";
        }

        String url = "http://ergast.com/api/f1/constructors/";
        url += constructorId;
        url += "/drivers";
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
            jsonData = (JSONObject) jsonData.get("DriverTable");
            JSONArray constructorDriverData = (JSONArray) jsonData.get("Drivers");
            String number;
            String code;

            for (Object i : constructorDriverData) {
                JSONObject info = (JSONObject) i;
                constructorDriver = new ConstructorDriver();

                try{
                    number = info.get("permanentNumber").toString();
                }catch (NullPointerException e){
                    number = " ";
                }

                try{
                    code = info.get("code").toString();
                }catch (NullPointerException e){
                    code = " ";
                }

                constructorDriver.setId(info.get("driverId").toString());
                constructorDriver.setNumber(number);
                constructorDriver.setCode(code);
                constructorDriver.setUrl(info.get("url").toString());
                constructorDriver.setName(info.get("givenName").toString());
                constructorDriver.setSurname(info.get("familyName").toString());
                constructorDriver.setDateOfBirth(info.get("dateOfBirth").toString());
                constructorDriver.setNationality(info.get("nationality").toString());

                constructorDriverList.add(constructorDriver);

            }
            constructor.setConstructorDriver(constructorDriverList);

        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }
}
