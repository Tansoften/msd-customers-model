package com.tansoften.msd.data;

import java.util.ArrayList;

public class Months {
  private  int id;
    private ArrayList<Consumption> consumptions = new ArrayList<>();

    public Months(int id) {
        this.id = id;
    }

    public void findCons(){
        System.out.println("hello");
    }

    public void setConsumptions(int year, int quantity) {
        if (consumptions.isEmpty()){
            Consumption consumption = new Consumption(year, quantity);
            consumptions.add(consumption);
        }else {
            consumptions.forEach(item -> {
                if (item.getId() != year){
                    Consumption consumption = new Consumption(year, quantity);
                    consumptions.add(consumption);
                }
            });
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
