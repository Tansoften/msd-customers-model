package com.tansoften.msd;

import net.minidev.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:8080/")
public class ModelController {
    //private static int count = 0;
    private static int waitTime = 10;
    //public static void resetCounters(){count=0;}
    public static void changeRate(int _waitTime){waitTime=_waitTime;}

    @RequestMapping(value="model/hospitals/consumptions/month/{month}", method=RequestMethod.GET)
    public ResponseEntity<?> getConsumptions(@PathVariable("month") int month){
        JSONObject consumptionsObj = new  JSONObject();

        Analysis.calcConsumptions(month);
        int consumptions = Analysis.getTotalConsumptions();
        consumptionsObj.put("consumptions", consumptions);

        return ResponseEntity.ok(consumptionsObj);
    }

    @RequestMapping(value="model/consumption/customer/{customer_id}/product/{product_id}/month/{month}", method=RequestMethod.GET)
    public ResponseEntity<?> getConsumption(@PathVariable("customer_id") int customerId, @PathVariable("product_id") String productId, @PathVariable("month") int month) throws InterruptedException {
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
//        System.out.print(usage);
//        if(month==1){
//            ++count;
//        }

        //System.out.println("Product: "+count+" Month:"+month);
        Thread.sleep(waitTime);
        return ResponseEntity.ok(product_usage);
    }
}
