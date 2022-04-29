package com.tansoften.msd;

import com.tansoften.msd.data.Consumption;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ModelTesting {
    private static int wins = 0;
    private static int loses = 0;
    private static int consumptionsLearned = 0;
    private static Double winRate;
    private static double standardDeviation = 0.0;

    public static int findHarmonicMean(ArrayList<Consumption> consumptions){
        int mean=0;
        AtomicReference<Double> totalReciprocal = new AtomicReference<>();
        ArrayList<Consumption> consumptionsCopy = (ArrayList<Consumption>) consumptions.clone();

        consumptionsCopy.stream().map(itemConsumption->{
            Double reciprocal = Math.pow(Double.valueOf(itemConsumption.getQuantity()), -1.0);
            totalReciprocal.getAndSet(reciprocal);
            return null;
        }).toList();

        mean = (int) ((consumptionsCopy.size())/totalReciprocal.get());

        return mean;
    }
    private static double mean = 0;

    private static int max = 0;

    private  static int min = 0;
    public static void setMax(int max) {
        ModelTesting.max = max;
    }

    public static void setMin(int min) {
        ModelTesting.min = min;
    }




    public static void setMean(double mean) {
        ModelTesting.mean = mean;
    }

    public static double getMean() {
        return mean;
    }

    public static int findMean(AtomicInteger total, int size) {
        try {
            int mean = total.get() / size;
            setMean(mean);
            return mean;
        } catch ( Exception exc ) {
            return STATUS.ZERO_DIVIDE.ordinal();
        }
    }

    public static void learnNewConsumption(){
        ++consumptionsLearned;
    }

    public static int getConsumptionsLearned(){return consumptionsLearned;}

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
        } catch (Exception exc) {
            System.out.println(exc);
        }

        return winRate;
    }

    public static void calculateStandardDeviation(ArrayList<Consumption> list) {
        AtomicReference<Double> variance = new AtomicReference<>(0.0);
        list.forEach(item -> {
            double dif = item.getQuantity() - getMean();
            variance.set(variance.get() + Math.pow(dif, 2.0));
        });

        standardDeviation = Math.sqrt(variance.get() / list.size());
    }

    public static double getStandardDeviation() {
        return standardDeviation;
    }

    public static int getSumOfThreeNo(ArrayList<Consumption> consumptions) {
        ArrayList<Integer> list = new ArrayList<>();
        consumptions.forEach(item -> list.add(item.getQuantity()));
        Integer[] newList = list.toArray(new Integer[0]);
        for (int i = 0; i < newList.length; i++) {
            for (int j = 1; j < newList.length; j++) {
                int swap;
                if (newList[j - 1] < newList[j]) {
                    swap = newList[j - 1];
                    newList[j - 1] = newList[j];
                    newList[j] = swap;
                }
            }
        }
        double firstNumbersCount = Math.ceil(newList.length * 0.4);
        double sum = 0;
        for (int i = 0; i < firstNumbersCount; i++) {
            sum = sum + newList[i];
        }
        return (int) Math.ceil(sum / firstNumbersCount);
    }

    public static int getRandomGhost(ArrayList<Consumption> consumptions){
        ArrayList<Integer> list = new ArrayList<>();
        consumptions.forEach(item->{
            list.add(item.getQuantity());
        });
        Integer[] listArray = list.toArray(new Integer[0]);
        int max = listArray.length;
        Random random = new Random();
        int index = random.nextInt(max);
        return listArray[index-1];
    }

    public static int getGeometricMean(ArrayList<Consumption> consumptions){
        ArrayList<Integer> list = new ArrayList<>();
        int sum = 0;
        double gMean = 0;
        consumptions.forEach(item->{
            list.add(item.getQuantity());
        });
        Integer[] newList = list.toArray(new Integer[0]);
        for (Integer i: newList){
            sum+=i;
        }
        gMean = Math.pow(sum,(1/newList.length));
        setMean(gMean);
        ModelTesting.calculateStandardDeviation(consumptions);
        return (int) Math.ceil(gMean);
    }
    public static int getLatestConsumption(ArrayList<Consumption> consumptions){
        ArrayList<Integer> list = new ArrayList<>();
        consumptions.forEach(item->{
            list.add(item.getQuantity());
        });
        Integer[] newList = list.toArray(new Integer[0]);
        return newList[newList.length-1];
    }
    public static int getMedianConsumption(ArrayList<Consumption> consumptions){
        ArrayList<Integer> list = new ArrayList<>();
        int index = 0;
        int median;
        consumptions.forEach(item->{
            list.add(item.getQuantity());
        });
        Integer[] newList = list.toArray(new Integer[0]);
        for (int i = 0; i < newList.length; i++) {
            for (int j = 1; j < newList.length; j++) {
                int swap;
                if (newList[j - 1] < newList[j]) {
                    swap = newList[j - 1];
                    newList[j - 1] = newList[j];
                    newList[j] = swap;
                }
            }
        }
        for(Integer i: newList){
            System.out.print(i+", ");
        }
        if(newList.length%2 == 1){
            index = (newList.length + 1) / 2;
            median = newList[index-1];
        }
        else {
            index = newList.length/2;
            median = (newList[index-1] + newList[index])/2;
        }

        System.out.println("\nMedian: "+median+", std: "+getStandardDeviation());
        return median;
    }
}
