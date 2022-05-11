package com.tansoften.msd.data;

public class Product {
    private String id;
   private Months january   = new Months(1);
   private Months february  = new Months(2);
   private Months march     = new Months(3);
   private Months april     = new Months(4);
   private Months may       = new Months(5);
   private Months june      = new Months(6);
   private Months july      = new Months(7);
   private Months august    = new Months(8);
   private Months september = new Months(9);
   private Months october   = new Months(10);
   private Months november  = new Months(11);
   private Months december  = new Months(12);

    public Months getJanuary() {
        return january;
    }

    public Months getFebruary() {
        return february;
    }

    public Months getMarch() {
        return march;
    }

    public Months getApril() {
        return april;
    }

    public Months getMay() {
        return may;
    }

    public Months getJune() {
        return june;
    }

    public Months getJuly() {
        return july;
    }

    public Months getAugust() {
        return august;
    }

    public Months getSeptember() {
        return september;
    }

    public Months getOctober() {
        return october;
    }

    public Months getNovember() {
        return november;
    }

    public Months getDecember() {
        return december;
    }

    public int findMonth(int mon){
       int quantity = 0;
       switch (mon){
           case 1   -> quantity = january.determineConsumption();
           case 2   -> quantity = february.determineConsumption();
           case 3   -> quantity = march.determineConsumption();
           case 4   -> quantity = april.determineConsumption();
           case 5   -> quantity = may.determineConsumption();
           case 6   -> quantity = june.determineConsumption();
           case 7   -> quantity = july.determineConsumption();
           case 8   -> quantity = august.determineConsumption();
           case 9   -> quantity = september.determineConsumption();
           case 10  -> quantity = october.determineConsumption();
           case 11  -> quantity = november.determineConsumption();
           case 12  -> quantity = december.determineConsumption();
       }

       return quantity;
   }

   public void passMonth(Date date, int quantity){

       switch (date.getMonth()){
           case 1   -> january.setConsumptions(date.getYear(), quantity);
           case 2   -> february.setConsumptions(date.getYear(), quantity);
           case 3   -> march.setConsumptions(date.getYear(), quantity);
           case 4   -> april.setConsumptions(date.getYear(), quantity);
           case 5   -> may.setConsumptions(date.getYear(), quantity);
           case 6   -> june.setConsumptions(date.getYear(), quantity);
           case 7   -> july.setConsumptions(date.getYear(), quantity);
           case 8   -> august.setConsumptions(date.getYear(), quantity);
           case 9   -> september.setConsumptions(date.getYear(), quantity);
           case 10  -> october.setConsumptions(date.getYear(), quantity);
           case 11  -> november.setConsumptions(date.getYear(), quantity);
           case 12  -> december.setConsumptions(date.getYear(), quantity);
       }
   }

    public Product(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
