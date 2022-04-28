package com.tansoften.msd;

public final class ModelTesting {
    private static int wins = 0;
    private static int loses = 0;
    private static Double winRate;

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
