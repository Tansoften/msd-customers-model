package com.tansoften.msd.data;

import com.tansoften.msd.ModelTesting;
import com.tansoften.msd.STATUS;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Months {
  private  int id;
    private ArrayList<Consumption> consumptions = new ArrayList<>();

    public Months(int id) {
        this.id = id;
    }

    public int determineConsumption(){
        AtomicInteger total= new AtomicInteger();
        consumptions.stream().map((consumption -> {
            total.addAndGet(consumption.getQuantity());
            return null;
        })).toList();



        try{
           ModelTesting.calculateStandardDeviation(consumptions);
           //return (int) ModelTesting.getSumOfThreeNo(consumptions);
           return ModelTesting.findMean(total, consumptions.size());
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
