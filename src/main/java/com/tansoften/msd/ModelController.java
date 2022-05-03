package com.tansoften.msd;

import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://192.168.43.207:8080/")
public class ModelController {
    @RequestMapping(value="model/consumption/customer/{customer_id}/product/{product_id}/month/{month}", method=RequestMethod.GET)
    public ResponseEntity<?> getConsumption(@PathVariable("customer_id") int customerId, @PathVariable("product_id") String productId, @PathVariable("month") int month){
        JSONObject product_usage = new  JSONObject();

        //receiving response from the Model
       int usage =  Model.getForecast(customerId,productId.trim(),month);
       int standardDeviation = (int) ModelTesting.getStandardDeviation();
       int min = usage-standardDeviation;
       int max = usage+standardDeviation;

       product_usage.put("product_usage",usage);
       product_usage.put("standard_deviation", standardDeviation);
       product_usage.put("min", (min < 0)?0:min);
       product_usage.put("max", max);
        //returning response to the client
        return ResponseEntity.ok(product_usage);
    }

}
