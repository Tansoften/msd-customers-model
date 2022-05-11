package com.tansoften.msd;

import com.tansoften.msd.data.Customer;
import com.tansoften.msd.data.Date;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Model {
    private static final ArrayList<Customer> root = new ArrayList<>();

    public ArrayList<Customer> getRoot(){return root;}

    public void loadValidatingData(){
        System.out.println("Fetching validating data...");
        JSONObject testingData = read_json("validating.json");
        JSONArray dataArray = (JSONArray) testingData.get("data");
        System.out.println("Done fetching validating data.");

        validateForecast(dataArray);
    }

    private void validateForecast(JSONArray dataArray){
        ModelTesting.resetWinsLoses();
        System.out.println("Testing model...");
        for (Object consumption : dataArray) {
            JSONObject data = (JSONObject) consumption;

            int customerId = Integer.parseInt((String) data.get("customer_id"));
            String productId = (String) data.get("product_id");
            int quantity = Integer.parseInt((String) data.get("quantity"));

            Date date = new Date(Integer.parseInt((String) data.get("year")), Integer.parseInt((String) data.get("month")));
            int futureConsumption = getForecast(customerId, productId.trim(), Integer.parseInt((String) data.get("month")));
            double std = ModelTesting.getStandardDeviation();

            if (futureConsumption == STATUS.ZERO_DIVIDE.ordinal()) {
                //System.out.println("Product with id "+productId.trim()+" was never used before.");
                learnNewConsumption(customerId, productId, date, quantity);
            } else if (quantity >= (futureConsumption - std) && quantity <= (futureConsumption + std)) {
                ModelTesting.addWins();
            } else {
                ModelTesting.addLoses();
                learnNewConsumption(customerId, productId, date, quantity);
            }
        }
        System.out.println("Testing phase completed successfully.");
    }

    private void learnNewConsumption(int customerId, String productId, Date date, int quantity){
        feedConsumption(customerId, productId, date, quantity);
        ModelTesting.learnNewConsumption();
        //System.out.println("Learned consumption of product "+productId);
    }

    public static int getForecast(int customer, String productId, int month){
        AtomicInteger futureConsumption = new AtomicInteger();

        root.forEach(item->{
            if(item.getId() == customer){
                futureConsumption.set(item.findProduct(productId, month));
            }
        });

        return futureConsumption.get();
    }

    private void cleanData(JSONArray dataArray) throws IOException {
        System.out.println("Cleaning data...");
        for(Object data: dataArray){
            JSONObject consumption = (JSONObject) data;
            int customerId = Integer.parseInt((String) consumption.get("customer_id"));
            String productId = (String) consumption.get("product_id");
            int quantity = Integer.parseInt((String) consumption.get("quantity"));
            Date date = new Date(Integer.parseInt((String) consumption.get("year")), Integer.parseInt((String) consumption.get("month")));
            if(Integer.parseInt((String) consumption.get("id")) < 21408 )
                continue;
            if(quantity == 0){
                int avgConsumption = getForecast(customerId, productId.trim(), date.getMonth());
                JSONObject updatingData = new JSONObject();
                updatingData.put("id", consumption.get("id"));
                updatingData.put("quantity", avgConsumption);
                JSONObject response = BackendService.API("PUT", "/update-consumption-quantity", updatingData);
//                if((boolean) response.get("hasUpdated")){
//                    System.out.println("Consumption of id "+consumption.get("id")+" was updated successfully.");
//                }else{
//                    System.out.println((String) response.get("message"));
//                }
            }
        }
        System.out.println("Done cleaning data.");
    }

    public void loadTree() throws IOException{
        System.out.println("Fetching training data...");
        JSONObject trainingData = read_json("training.json");
        JSONArray dataArray = (JSONArray) trainingData.get("data");
        System.out.println("Done fetching training data.");

        System.out.println("Initializing model...");

        for (Object consumption : dataArray) {
            JSONObject data = (JSONObject) consumption;
            int customerId = Integer.parseInt((String) data.get("customer_id"));
            String productId = (String) data.get("product_id");
            int quantity = Integer.parseInt((String) data.get("quantity"));

            Date date = new Date(Integer.parseInt((String) data.get("year")), Integer.parseInt((String) data.get("month")));

            feedConsumption(customerId, productId.trim(), date, quantity);
        }
        System.out.println("Model was initialized successfully.");

        //cleanData(dataArray);
    }

    private void feedConsumption(int customerId, String productId, Date date, int quantity){
        AtomicBoolean hasFound = new AtomicBoolean(false);
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

    @SuppressWarnings("TryWithIdenticalCatches")
    private JSONObject read_json(String fileName) {
        @SuppressWarnings("deprecation") JSONParser jsonParser = new JSONParser();
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
