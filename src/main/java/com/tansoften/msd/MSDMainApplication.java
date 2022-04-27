package com.tansoften.msd;

import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.boot.SpringApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MSDMainApplication {
<<<<<<< HEAD
    private ArrayList<Customer> root = new ArrayList<>();
    private JSONArray data;
    public static void main(String[] args) {
            MSDMainApplication msdMainApplication = new MSDMainApplication();
            msdMainApplication.read_json();
            msdMainApplication.loadTree();
    }

    private void loadTree(){
         for(datum:data) {
            Customer customer = new Customer();
            customer.setId(datum.get("id"));
        }
=======
    public static void main(String[] args) {
            MSDMainApplication msdMainApplication = new MSDMainApplication();
            msdMainApplication.read_json();
>>>>>>> c8d4a13f8ffc363432162155d261c8cb0ed6065d
    }

    private JSONArray read_json() {
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader("src/main/java/com/tansoften/msd/consumption_facts.json");
            Object jsonObject = jsonParser.parse(reader);
            JSONArray consumptionList = (JSONArray) jsonObject;
            data = consumptionList;
        } catch ( FileNotFoundException e ) {
            throw new RuntimeException(e);
        } catch ( ParseException e ) {
            throw new RuntimeException(e);
        }
    }
}
