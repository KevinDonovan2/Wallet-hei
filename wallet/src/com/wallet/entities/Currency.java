package com.wallet.entities;
public class Currency {
   private int currencyId;
   private String currencyName;
   private String currencyCode;

   public Currency(int currencyId, String currencyName, String currencyCode){
    this.currencyId = currencyId;
    this.currencyName = currencyName;
    this.currencyCode = currencyCode;
   }

   public int getCurrencyId(){
    return currencyId;
   }
   public String getCurrencyName(){
    return currencyName;
   }
   public String getCurrencyCode(){
    return currencyCode;
   }
    public int setCurrencyId(){
    return currencyId;
   }
   public String setCurrencyName(){
    return currencyName;
   }
   public String setCurrencyCode(){
    return currencyCode;
   }
   @Override
   public String toString(){
    return "Currency: \n" +
            "id currency: " + currencyId + "\n" +
            "your currency: " + currencyName + "\n" +
            "it code: " + currencyCode;
   } 
}
