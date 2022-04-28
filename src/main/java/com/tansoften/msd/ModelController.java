package com.tansoften.msd;

import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
//@CrossOrigin("http://localhost:8000/")
public class ModelController {
    //@RequestMapping(value = "/model", method = RequestMethod.POST)
    //public ResponseEntity<?> getConsumption(@RequestBody Map<String, Object> order){
    @GetMapping("/model")
    public ResponseEntity<?> getConsumption(){
        int customer = 1;
        //int customer = order.get("customer_id");
        String product = "01102301";
        int month = 2;
        JSONObject jsonObject = new  JSONObject();
        jsonObject.put("product_id","1010101");
        jsonObject.put("customer_id",1);
        jsonObject.put("quantity",200);
        MSDMainApplication msdMainApplication = new MSDMainApplication();
        //receiving response from the Model
        msdMainApplication.traverse(customer,product,month);
        //returning response to the client
        return ResponseEntity.ok(jsonObject);
    }

}
