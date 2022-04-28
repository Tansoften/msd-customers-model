package com.tansoften.msd;

import com.tansoften.msd.data.Consumption;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public final class ModelTesting {
    private static int wins = 0;
    private static int loses = 0;
    private static Double winRate;

    private static double standardDeviation = 0.0;

    public static void addWins() {
    public static int findMean(AtomicInteger total, int size){
        try{
            int mean = total.get() /size;
            return mean;
        }catch (Exception exc){
            return STATUS.ZERO_DIVIDE.ordinal();
        }
    }

    public static void addWins(){
        ++wins;
    }

    public static void addLoses() {
        ++loses;
    }

    public static int getWins() {
        return wins;
    }

    public static int getLoses() {
        return loses;
    }

    public static Double getWinRate() {
        try {
            winRate = Double.valueOf(wins / (loses + wins));
        } catch ( Exception exc ) {
            System.out.println(exc);
        }

        return winRate;
    }

    public static void calculateStandardDeviation(ArrayList<Consumption> list) {
        AtomicInteger sum = new AtomicInteger();
        int mean = 0;
        double std= 0;
        list.forEach(item -> {
            sum.addAndGet(item.getId());
        });

        mean = sum.get() / (list.size());
        list.forEach(item -> {

        });
        for (int i = 0; i < list.size(); i++) {
            std[0] = std[0] + Math.pow((list[i] - mean), 2);
        }
        standardDeviation = Math.sqrt(std[0] / list.size());
    }

    public static double getStandardDeviation() {
        return standardDeviation;
    }
}
