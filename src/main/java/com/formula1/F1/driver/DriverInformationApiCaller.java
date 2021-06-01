package com.formula1.F1.driver;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DriverInformationApiCaller {
    private String driverId;
    private DriverInformation driverInformation;


    DriverInformationApiCaller(String driverId) {

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
                System.out.println("Code: " + status);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    void getDriverInformation(Driver driver) {
        if(driverId == ""){
            driverId = "none";
        }

        String url = "http://ergast.com/api/f1/drivers/";
        url += driverId;
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
            JSONArray driverData = (JSONArray) jsonData.get("Drivers");

            if(driverData.size() != 0) {
                JSONObject info = (JSONObject) driverData.get(0);
                String number;
                String code;

                driverInformation = new DriverInformation();
                try {

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

                    driverInformation.setDriverId(info.get("driverId").toString());
                    driverInformation.setNumber(number);
                    driverInformation.setCode(code);
                    driverInformation.setUrl(info.get("url").toString());
                    driverInformation.setName(info.get("givenName").toString());
                    driverInformation.setSurname(info.get("familyName").toString());
                    driverInformation.setDateOfBirth(info.get("dateOfBirth").toString());
                    driverInformation.setNationality(info.get("nationality").toString());
                }catch (Exception e){
                    e.printStackTrace();
                }

                driver.setDriverInformation(driverInformation);
            }
        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
    }
}
