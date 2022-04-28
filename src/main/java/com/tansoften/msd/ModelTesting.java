package com.tansoften.msd;

import java.util.concurrent.atomic.AtomicInteger;

public final class ModelTesting {
    private static int wins = 0;
    private static int loses = 0;
    private static Double winRate;

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
    public static void addLoses(){
        ++loses;
    }

    public static int getWins(){return wins;}
    public static int getLoses(){return loses;}

    public static Double getWinRate(){
        try{
            winRate = Double.valueOf(wins/(loses+wins));
        }catch (Exception exc){
            System.out.println(exc);
        }

        return winRate;
    }
}
