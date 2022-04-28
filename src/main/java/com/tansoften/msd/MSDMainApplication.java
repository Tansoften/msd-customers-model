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
        msdMainApplication.loadTree();
        msdMainApplication.loadAndTest();
        System.out.println("Win rate: "+ModelTesting.getWinRate()*100+"\nWins: "+ModelTesting.getWins()+"\nLoses: "+ModelTesting.getLoses());
    }

    private void loadAndTest(){
        JSONObject testingData = read_json("testing.json");
        JSONArray dataArray = (JSONArray) testingData.get("data");

        for(int index = 0; index < dataArray.size(); ++index){
            JSONObject data = (JSONObject) dataArray.get(index);
            int quantity = Integer.parseInt((String) data.get("quantity"));
            int futureConsumption = testForecast(Integer.parseInt((String) data.get("customer_id")) , String.valueOf(data.get("product_id")), Integer.parseInt((String) data.get("month")) );
            Double std = ModelTesting.getStandardDeviation();
            if(futureConsumption == STATUS.ZERO_DIVIDE.ordinal()){
                System.out.println("skipped");
            }
            else if(quantity >= (futureConsumption-std) && quantity <= (futureConsumption+std)){
                ModelTesting.addWins();
            }else{
                ModelTesting.addLoses();
            }
        }
    }

    private int testForecast(int customerId, String productId, int month){
        int forecastNo = getForecast(customerId, productId, month);
        return forecastNo;
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

    public void traverse(int customer, String productId, int month){
        root.forEach(item->{
            if(item.getId() == customer){
                item.findProduct(productId, month);
            }
        });

    }

    private void loadTree(){
        data = read_json("training.json");
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

    private JSONObject read_json(String fileName) {
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader("src/main/java/com/tansoften/msd/"+fileName);
            Object jsonObject = jsonParser.parse(reader);
            JSONArray consumptionList = (JSONArray) jsonObject;
            return (JSONObject) consumptionList.get(2);
        } catch ( FileNotFoundException e ) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
