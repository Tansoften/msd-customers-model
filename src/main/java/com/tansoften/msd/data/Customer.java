package com.tansoften.msd.data;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer {
   private int id;
   private ArrayList<Product> productsList = new ArrayList<>();

   public int findProduct(String pid, int mon){
       AtomicInteger futureConsumption = new AtomicInteger();
       productsList.forEach(item->{
           if(item.getId().equals(pid)){
               futureConsumption.set(item.findMonth(mon));
           }
       });

       return futureConsumption.get();
   }

    public void setProduct(String productId, Date date, int quantity){
        AtomicBoolean hasFound = new AtomicBoolean(false);

        productsList.stream().map((itemProduct -> {
            if (itemProduct.getId().equals(productId)){
                itemProduct.setId(productId);
                itemProduct.passMonth(date, quantity);
                hasFound.set(true);
            }
            return null;
        })).toList();

        if(!hasFound.get()){
            Product product = new Product(productId);
            product.passMonth(date, quantity);
            productsList.add(product);
        }
    }

    public Customer(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
