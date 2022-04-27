package com.tansoften.msd;

import com.tansoften.msd.data.Customer;
import org.springframework.boot.SpringApplication;

import java.util.ArrayList;

public class MSDMainApplication {
    private ArrayList<Customer> root = new ArrayList<>();

    public static void main(String[] args) {
        for(datum:data){
            Customer customer = new Customer();
            customer.setId(datum.get("id"));
        }
    }
}
