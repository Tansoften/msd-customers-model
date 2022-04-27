package com.tansoften.msd;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MSDMainApplication {
    public static void main(String[] args) {
            MSDMainApplication msdMainApplication = new MSDMainApplication();
            msdMainApplication.read_json_data();
    }

    private void read_json_data() {
        JSONParser jsonParser = new JSONParser();
        try {
            File file = new File("src/main/java/com/tansoften/msd/consumption_facts.json");
            FileReader reader = new FileReader(file);
            JSONArray jsonOArray = (JSONArray) jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) jsonOArray.get(2);
            System.out.println(jsonObject.get("data"));
        } catch ( FileNotFoundException e ) {
            throw new RuntimeException(e);
        } catch ( ParseException e ) {
            throw new RuntimeException(e);
        }
    }
}
