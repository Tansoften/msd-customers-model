package com.tansoften.msd.data;

import com.tansoften.msd.ModelTesting;
import com.tansoften.msd.STATUS;

import java.util.ArrayList;

public class Months {
    private  int id;
    private ArrayList<Consumption> consumptions = new ArrayList<>();

    public Months(int id) {
        this.id = id;
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
