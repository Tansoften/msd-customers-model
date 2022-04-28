package com.tansoften.msd;


import com.tansoften.msd.data.Customer;
import com.tansoften.msd.data.Date;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class MSDMainApplication {
    private ArrayList<Customer> root = new ArrayList<>();
    private JSONObject data;

    public static void main(String[] args) {
        MSDMainApplication msdMainApplication = new MSDMainApplication();
        msdMainApplication.read_json();
        msdMainApplication.loadTree();
        msdMainApplication.testForecast(1, "00000802", 9);
        //System.out.println(msdMainApplication.root.size());
    }

    private void testForecast(int customerId, String productId, int month){
        int forecastNo = getForecast(customerId, productId, month);
        System.out.println(forecastNo);
    }

    private int getForecast(int customer, String productId, int month){
        AtomicInteger futureConsumption = new AtomicInteger();
        root.forEach(item->{
            if(item.getId() == customer){
                futureConsumption.set(item.findProduct(productId, month));
            }
        });

        return futureConsumption.get();
    }

    private void traverse(int customer, String productId, int month){
        root.forEach(item->{
            if(item.getId() == customer){
                item.findProduct(productId, month);
            }
        });
    }

    private void loadTree(){
        JSONArray dataArray = (JSONArray) data.get("data");

         for(int index=0; index < dataArray.size(); ++index) {
             JSONObject data = (JSONObject) dataArray.get(index);
             int customerId = Integer.parseInt((String) data.get("customer_id"));
             String productId = (String) data.get("product_id");
             int quantity = Integer.parseInt((String) data.get("quantity"));
             AtomicBoolean hasFound = new AtomicBoolean(false);
             Date date = new Date(Integer.parseInt((String) data.get("year")), Integer.parseInt((String) data.get("month")));

             root.forEach(itemCustomer -> {
                if(itemCustomer.getId() == customerId){
                    itemCustomer.setProduct(productId, date, quantity);
                    hasFound.set(true);
                }
            });

            if(!hasFound.get()){
                Customer customer = new Customer(customerId);
                customer.setProduct(productId, date, quantity);
                root.add(customer);
            }
        }
    }

    private void read_json() {
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader("src/main/java/com/tansoften/msd/consumption_facts.json");
            Object jsonObject = jsonParser.parse(reader);
            JSONArray consumptionList = (JSONArray) jsonObject;
            data = (JSONObject) consumptionList.get(2);
        } catch ( FileNotFoundException e ) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}
