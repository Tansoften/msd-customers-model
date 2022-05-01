package com.tansoften.msd;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BackendService extends Thread{
    private static HttpURLConnection connection;
    public static boolean hasFinished = false;

    public static JSONObject API(String httpMethod, String link, JSONObject data) throws IOException {

        BufferedReader reader;
        String line;
        StringBuffer response = new StringBuffer();
        String api = "http://localhost:8000/api" + link;
        JSONObject jsonObject = new JSONObject();

        try {
            URL url = new URL(api);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(httpMethod);
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(15000);
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            try{
                connection.connect();
            }
            catch ( Exception e ){
                jsonObject.put("error","Connection not established");
                hasFinished = true;
                return jsonObject;
            }

            JSONParser jsonParser = new JSONParser();
            //send get http method
            if(httpMethod != "GET"){
                try {
                    byte[] out = data.toString().getBytes(StandardCharsets.UTF_8);
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(out);
                }
                catch ( Exception e){
                    System.out.println("Error in sending");
                    e.printStackTrace();
                }

            }
            if(connection.getResponseCode() == 403){
                jsonObject.put("Error", "Forbidden");
                System.out.println(connection.getResponseCode());
            }
            else if(connection.getResponseCode() == 401){
                jsonObject.put("Error", "Unauthorized");
                System.out.println(connection.getResponseCode());
            }
            else if(connection.getResponseCode() == 200) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                //convert response into JSON format
                jsonObject = (JSONObject) jsonParser.parse(response.toString());
                System.out.println(jsonParser.parse(response.toString()));
            }
            else{
                jsonObject.put("message", "Some errors occurred");
                System.out.println(connection.getResponseCode());
            }

        } catch ( MalformedURLException e ) {
            System.out.println("Errors");
        } catch ( IOException e ) {
            e.printStackTrace();
        } catch ( ParseException e ) {
            e.printStackTrace();
        }
        hasFinished = true;
        jsonObject.put("code", connection.getResponseCode());
        connection.disconnect();

        return jsonObject;
    }
}