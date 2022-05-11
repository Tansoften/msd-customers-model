package com.tansoften.msd;

import com.tansoften.msd.data.Customer;
import com.tansoften.msd.data.Product;

public class Analysis {
    private static int totalConsumptions;
    private static final Model model = new Model();

    public static int getTotalConsumptions() {
        return totalConsumptions;
    }

    public static void setTotalConsumptions(int totalConsumptions) {
        Analysis.totalConsumptions = totalConsumptions;
    }

    public static void cumulateConsumptions(int sum){
        totalConsumptions += sum;
    }

    public static void calcConsumptions(int customerId, String productId, int month){
        setTotalConsumptions(0);

        for(Customer customer:model.getRoot()){
            if(customer.getId() == customerId){
                for(Product product:customer.getProductsList()){
                    if(product.getId().equals(productId)){
                        int consumptions = 0;

                        switch (month){
                            case 1   -> consumptions = product.getJanuary().getTotalConsumptions();
                            case 2   -> consumptions = product.getFebruary().getTotalConsumptions();
                            case 3   -> consumptions = product.getMarch().getTotalConsumptions();
                            case 4   -> consumptions = product.getApril().getTotalConsumptions();
                            case 5   -> consumptions = product.getMay().getTotalConsumptions();
                            case 6   -> consumptions = product.getJune().getTotalConsumptions();
                            case 7   -> consumptions = product.getJuly().getTotalConsumptions();
                            case 8   -> consumptions = product.getAugust().getTotalConsumptions();
                            case 9   -> consumptions = product.getSeptember().getTotalConsumptions();
                            case 10  -> consumptions = product.getOctober().getTotalConsumptions();
                            case 11  -> consumptions = product.getNovember().getTotalConsumptions();
                            case 12  -> consumptions = product.getDecember().getTotalConsumptions();
                        }
                        cumulateConsumptions(consumptions);
                    }
                }
            }
        }
    }

    public static void calcConsumptions(int customerId, int month){
        setTotalConsumptions(0);

        for(Customer customer:model.getRoot()){
            if(customer.getId() == customerId){
                for(Product product:customer.getProductsList()){
                    int customerMonthlySum = 0;

                    switch (month){
                        case 1   -> customerMonthlySum = product.getJanuary().getTotalConsumptions();
                        case 2   -> customerMonthlySum = product.getFebruary().getTotalConsumptions();
                        case 3   -> customerMonthlySum = product.getMarch().getTotalConsumptions();
                        case 4   -> customerMonthlySum = product.getApril().getTotalConsumptions();
                        case 5   -> customerMonthlySum = product.getMay().getTotalConsumptions();
                        case 6   -> customerMonthlySum = product.getJune().getTotalConsumptions();
                        case 7   -> customerMonthlySum = product.getJuly().getTotalConsumptions();
                        case 8   -> customerMonthlySum = product.getAugust().getTotalConsumptions();
                        case 9   -> customerMonthlySum = product.getSeptember().getTotalConsumptions();
                        case 10  -> customerMonthlySum = product.getOctober().getTotalConsumptions();
                        case 11  -> customerMonthlySum = product.getNovember().getTotalConsumptions();
                        case 12  -> customerMonthlySum = product.getDecember().getTotalConsumptions();
                    }
                    cumulateConsumptions(customerMonthlySum);
                }
            }
        }
    }

    public static void calcConsumptions(int month){
        setTotalConsumptions(0);

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
