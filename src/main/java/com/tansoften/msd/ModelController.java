package com.tansoften.msd;

import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("http://localhost:8080/")
public class ModelController {
    //@RequestMapping(value = "/model/product_usage", method = RequestMethod.POST)
    //public ResponseEntity<?> getConsumption(@RequestBody Map<String, Object> product_order)
    @RequestMapping(value="model/product_usage", method=RequestMethod.GET)
    public ResponseEntity<?> getConsumption(@RequestParam("customer_id") int customerId, @RequestParam("product_id") String productId, @RequestParam("month") int month){
//        int customer = (int) product_order.get("customer_id");
//        String product = (String) product_order.get("product_id");
//        int month = (int) product_order.get("month");
        JSONObject product_usage = new  JSONObject();

        //receiving response from the Model
       int usage =  MSDMainApplication.getForecast(customerId,productId.trim(),month);
       product_usage.put("product_usage",usage);
        //returning response to the client
        return ResponseEntity.ok(product_usage);
    }

}
