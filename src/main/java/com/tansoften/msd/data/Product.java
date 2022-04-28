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

   public void findMonth(int mon){
       switch (mon){
           case 1   -> january.findCons();
//           case 2   -> february.setConsumptions(date.getYear(), quantity);
//           case 3   -> march.setConsumptions(date.getYear(), quantity);
//           case 4   -> april.setConsumptions(date.getYear(), quantity);
//           case 5   -> may.setConsumptions(date.getYear(), quantity);
//           case 6   -> june.setConsumptions(date.getYear(), quantity);
//           case 7   -> july.setConsumptions(date.getYear(), quantity);
//           case 8   -> august.setConsumptions(date.getYear(), quantity);
//           case 9   -> september.setConsumptions(date.getYear(), quantity);
//           case 10  -> october.setConsumptions(date.getYear(), quantity);
//           case 11  -> november.setConsumptions(date.getYear(), quantity);
//           case 12  -> december.setConsumptions(date.getYear(), quantity);
       }
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
