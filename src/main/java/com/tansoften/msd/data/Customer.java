package com.tansoften.msd.data;

import java.util.ArrayList;

public class Customer {
   private int id;
   private ArrayList<Product> productsList = new ArrayList<>();

   public void findProduct(String pid, int mon){
       productsList.forEach(item->{
           if(item.getId().equals(pid)){
               item.findMonth(mon);
           }
       });
   }

    public void setProduct(String productId, Date date, int quantity){
        if (productsList.isEmpty()){
            Product product = new Product(productId);
            product.passMonth(date, quantity);
            productsList.add(product);
        }else {
            productsList.forEach(itemProduct -> {
                if (itemProduct.getId() != productId){
                    Product product = new Product(productId);
                    product.passMonth(date, quantity);
                    productsList.add(product);
                }
            });
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
