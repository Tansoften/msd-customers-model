package com.tansoften.msd;

import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("http://localhost:8000/")
public class ModelController {
    //@RequestMapping(value = "/model/product_usage", method = RequestMethod.POST)
    //public ResponseEntity<?> getConsumption(@RequestBody Map<String, Object> product_order)
    @GetMapping("/model/product_usage")
    public ResponseEntity<?> getConsumption(){
//        int customer = (int) product_order.get("customer_id");
//        String product = (String) product_order.get("product_id");
//        int month = (int) product_order.get("month");
        JSONObject product_usage = new  JSONObject();
        MSDMainApplication msdMainApplication = new MSDMainApplication();
        //receiving response from the Model
       int usage =  msdMainApplication.traverse(1,"01102301",2);
       product_usage.put("product_usage",usage);
        //returning response to the client
        return ResponseEntity.ok(product_usage);
    }

}
