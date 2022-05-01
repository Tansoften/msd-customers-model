package com.tansoften.msd;

import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("http://127.0.0.1:8000/")
public class ModelController {
   // @GetMapping("/model/product_usage")
    @RequestMapping(value = "/model/product_usage", method = RequestMethod.POST)
    public ResponseEntity<?> getConsumption(@RequestBody Map<String, Object> product_order){
   // public ResponseEntity<?> getConsumption(){
        int customer = Integer.parseInt((String) product_order.get("customer_id"));
        String product = (String) product_order.get("product_id");
        int month = Integer.parseInt((String) product_order.get("month"));
        JSONObject product_usage = new  JSONObject();
        MSDMainApplication msdMainApplication = new MSDMainApplication();
        msdMainApplication.loadTree();
        //receiving response from the Model
       int usage =  msdMainApplication.getForecast(customer,product,month);
       product_usage.put("product_usage",usage);
        //returning response to the client
        return ResponseEntity.ok(product_usage);
    }

}
