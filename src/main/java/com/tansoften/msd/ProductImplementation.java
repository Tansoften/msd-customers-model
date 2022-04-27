package com.tansoften.msd;

import com.tansoften.msd.data.Customer;
import com.tansoften.msd.data.Product;

import java.util.ArrayList;

public class ProductImplementation {

    int productId;
    ArrayList<Product> productsList = new ArrayList<>();

    ProductImplementation(int productId){
        this.productId = productId;
    }



    private void setProduct(int productId){
        if (productsList.isEmpty()){
            Product product = new Product(this.productId);
            productsList.add(product);
        }else {
            productsList.forEach(itemProduct -> {
                if (itemProduct.getId() != productId){
                    Product product = new Product(this.productId);
                    productsList.add(product);
                }
            });
        }
    }


}
