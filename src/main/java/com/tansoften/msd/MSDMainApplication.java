package com.tansoften.msd;

import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.boot.SpringApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MSDMainApplication {
    public static void main(String[] args) {
            MSDMainApplication msdMainApplication = new MSDMainApplication();
            msdMainApplication.read_json();
    }

    private void read_json() {
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader("src/main/java/com/tansoften/msd/consumption_facts.json");
            Object jsonObject = jsonParser.parse(reader);
            JSONArray consumptionList = (JSONArray) jsonObject;
            System.out.println(consumptionList);
        } catch ( FileNotFoundException e ) {
            throw new RuntimeException(e);
        } catch ( ParseException e ) {
            throw new RuntimeException(e);
        }
    }
}
