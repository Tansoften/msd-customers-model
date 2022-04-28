package com.tansoften.msd;

import com.tansoften.msd.data.Consumption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ModelTesting {
    private static int wins = 0;
    private static int loses = 0;
    private static Double winRate;

    private static double standardDeviation = 0.0;

    public static int findMean(AtomicInteger total, int size) {
        try {
            int mean = total.get() / size;
            return mean;
        } catch (Exception exc) {
            return STATUS.ZERO_DIVIDE.ordinal();
        }
    }

    public static void addWins() {
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
            winRate = Double.valueOf(Double.valueOf(wins) / (Double.valueOf(loses) + Double.valueOf(wins)));
            System.out.println(wins / (loses + wins));
        } catch (Exception exc) {
            System.out.println(exc);
        }

        return winRate;
    }

    public static void calculateStandardDeviation(ArrayList<Consumption> list) {
        AtomicInteger sum = new AtomicInteger();
        AtomicInteger mean = new AtomicInteger(0);
        AtomicReference<Double> variance = new AtomicReference<>(0.0);
        list.forEach(item -> {
            sum.addAndGet(item.getQuantity());
        });

        mean.set(sum.get() / (list.size()));

        list.forEach(item -> {
            double dif = item.getQuantity() - mean.get();
            variance.set(variance.get() + Math.pow(dif, 2.0));
        });

        standardDeviation = Math.sqrt(variance.get() / list.size());
    }

    public static double getStandardDeviation() {
        return standardDeviation;
    }

    public static double getSumOfThreeNo(ArrayList<Consumption> consumptions) {
       // ArrayList<Consumption> newList = new ArrayList<>();
        int[] newList = new int[consumptions.size()];
        int sum;
        ArrayList<Consumption> newConsumptions = consumptions;
       AtomicInteger count = new AtomicInteger();
        newConsumptions.forEach(item -> {
                newList[0] =item.getQuantity();
                count.getAndIncrement();
        });

        for(int i= 0; i<newList.length; i++){
            for(int j= 1; j<newList.length; j++){
                if(newList[i]<newList[j]){
                    newList[j] = newList[i];
                }
            }
        }
        int[] arr= Arrays.copyOfRange(newList, newList.length-3, newList.length);

        return Arrays.stream(arr).sum()/3;
    }
}
