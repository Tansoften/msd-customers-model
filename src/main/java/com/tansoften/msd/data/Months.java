package com.tansoften.msd.data;

import com.tansoften.msd.ModelTesting;
import com.tansoften.msd.STATUS;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Months {
    private  int id;
    private ArrayList<Consumption> consumptions = new ArrayList<>();

    public Months(int id) {
        this.id = id;
    }

    public int getTotalConsumptions(){
        AtomicInteger total = new AtomicInteger(0);

        consumptions.forEach(consumption->{
            total.addAndGet(consumption.getQuantity());
        });
        return total.get();
    }

    public int determineConsumption(){
        try{
            //return ModelTesting.getSumOfThreeNo(consumptions);
            //return ModelTesting.getRandomGhost(consumptions);
            //return ModelTesting.calculateMedianConsumption(consumptions);
            //return ModelTesting.getLatestConsumption(consumptions);
            //return ModelTesting.calculateGeometricMean(consumptions);
            return ModelTesting.calculateArithmeticMean(consumptions);
        }catch (Exception exc){
            return STATUS.ZERO_DIVIDE.ordinal();
        }
    }

    public void setConsumptions(int year, int quantity) {
        Consumption consumption = new Consumption(year, quantity);
        consumptions.add(consumption);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
