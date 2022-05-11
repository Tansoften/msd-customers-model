package com.tansoften.msd;

import com.tansoften.msd.data.Customer;
import com.tansoften.msd.data.Product;

public class Analysis {
    private static int totalConsumptions;

    public static int getTotalConsumptions() {
        return totalConsumptions;
    }

    public static void setTotalConsumptions(int totalConsumptions) {
        Analysis.totalConsumptions = totalConsumptions;
    }

    public static void cumulateConsumptions(int sum){
        totalConsumptions += sum;
    }

    public static void calcConsumptions(int month){
        setTotalConsumptions(0);
        Model model = new Model();
        for(Customer customer:model.getRoot()){
            for(Product product:customer.getProductsList()){
                int monthlySum = 0;
                switch (month){
                    case 1   -> monthlySum = product.getJanuary().getTotalConsumptions();
                    case 2   -> monthlySum = product.getFebruary().getTotalConsumptions();
                    case 3   -> monthlySum = product.getMarch().getTotalConsumptions();
                    case 4   -> monthlySum = product.getApril().getTotalConsumptions();
                    case 5   -> monthlySum = product.getMay().getTotalConsumptions();
                    case 6   -> monthlySum = product.getJune().getTotalConsumptions();
                    case 7   -> monthlySum = product.getJuly().getTotalConsumptions();
                    case 8   -> monthlySum = product.getAugust().getTotalConsumptions();
                    case 9   -> monthlySum = product.getSeptember().getTotalConsumptions();
                    case 10  -> monthlySum = product.getOctober().getTotalConsumptions();
                    case 11  -> monthlySum = product.getNovember().getTotalConsumptions();
                    case 12  -> monthlySum = product.getDecember().getTotalConsumptions();
                }
                cumulateConsumptions(monthlySum);
            }
        }
    }
}
