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

    private static double median_margin = 0;

    public static void setMedian_margin(double median_margin) {
        ModelTesting.median_margin = median_margin;
    }

    public static double getMedian_margin() {
        return median_margin;
    }

    public static void setMean(double mean) {
        ModelTesting.mean = mean;
    }

    public static double getMean() {
        return mean;
    }

    public static int calculateArithmeticMean(ArrayList<Consumption> consumptions) {
        try {
            AtomicInteger total= new AtomicInteger();
            consumptions.stream().map((consumption -> {
                total.addAndGet(consumption.getQuantity());
                return null;
            })).toList();

            int mean = total.get() / consumptions.size();
            setMean(mean);
            calculateStandardDeviation(consumptions);
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

    public static void resetWinsLoses(){
        wins    = 0;
        loses   = 0;
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

    private static void calculateGSD(ArrayList<Consumption> consumptions){
        AtomicReference<Double> lnRationSquaredSum = new AtomicReference<>(0.0);

        consumptions.forEach(consumption -> {
            double lnRation = ln(consumption.getQuantity()/getMean());
            lnRationSquaredSum.set(lnRationSquaredSum.get()+Math.pow(lnRation, 2));
        });

        double variance = lnRationSquaredSum.get()/consumptions.size();

        standardDeviation = Math.exp(Math.sqrt(variance));
    }

    private static double ln(Double number){
        double ln = (-Math.log(1-number))/number;
        return ln;
    }

    public static void calculateStandardDeviation(ArrayList<Consumption> list) {
        AtomicReference<Double> diffOfNumAndMeanSquared = new AtomicReference<>(0.0);
        list.forEach(item -> {
            double dif = item.getQuantity() - getMean();
            diffOfNumAndMeanSquared.set(diffOfNumAndMeanSquared.get() + Math.pow(dif, 2.0));
        });

        double variance = diffOfNumAndMeanSquared.get() / list.size();

        standardDeviation = Math.sqrt(variance);
    }

    public static double getStandardDeviation() {
        return standardDeviation;
    }

    public static int calculateSumOfThreeNo(ArrayList<Consumption> consumptions) {
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

    public static int calculateRandomGhost(ArrayList<Consumption> consumptions){
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

    public static int calculateGeometricMean(ArrayList<Consumption> consumptions){
        ArrayList<Integer> list = new ArrayList<>();
        int product = 1;
        double gMean = 0;
        consumptions.forEach(item->{
            list.add(item.getQuantity());
        });
        Integer[] newList = list.toArray(new Integer[0]);
        for (Integer i: newList){
            product*=i;
        }
        gMean = Math.pow(product,(1/newList.length));
        setMean(gMean);
        ModelTesting.calculateGSD(consumptions);
        return (int) Math.ceil(gMean);
    }
    public static int calculateLatestConsumption(ArrayList<Consumption> consumptions){
        ArrayList<Integer> list = new ArrayList<>();
        consumptions.forEach(item->{
            list.add(item.getQuantity());
        });
        Integer[] newList = list.toArray(new Integer[0]);
        return newList[newList.length-1];
    }
    public static int calculateMedianConsumption(ArrayList<Consumption> consumptions){
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
        if(newList.length%2 == 1){
            index = (newList.length + 1) / 2;
            median = newList[index-1];
        }
        else {
            index = newList.length/2;
            median = (newList[index-1] + newList[index])/2;
        }
        setMedian_margin((newList[0] - newList[newList.length-1])/2);
        return median;
    }
}
