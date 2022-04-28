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


public class MSDMainApplication {
    private ArrayList<Customer> root = new ArrayList<>();
    private JSONObject data;

    public static void main(String[] args) {
<<<<<<< HEAD
            MSDMainApplication msdMainApplication = new MSDMainApplication();
            msdMainApplication.read_json();
           // msdMainApplication.loadTree();
=======
        MSDMainApplication msdMainApplication = new MSDMainApplication();
        msdMainApplication.read_json();
        msdMainApplication.loadTree();
        msdMainApplication.traverse(1, "00000802", 1);
    }

    private void traverse(int customer, String productId, int month){
        root.forEach(item->{
            if(item.getId() == customer){
                item.findProduct(productId, month);
            }
        });
>>>>>>> 0a47fedc6da4840439578786154b63bbb737336b
    }

    private void loadTree(){
        JSONArray dataArray = (JSONArray) data.get("data");

         for(int index=0; index < dataArray.size(); ++index) {
            JSONObject data = (JSONObject) dataArray.get(index);
            Customer customer = new Customer(Integer.parseInt((String) data.get("customer_id")) );
            Date date = new Date(Integer.parseInt((String) data.get("year")), Integer.parseInt((String) data.get("month")));
            customer.setProduct((String) data.get("product_id"), date, Integer.parseInt((String) data.get("quantity")));
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
