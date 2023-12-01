package com.wallet.entities;
public class Currency {
    private int id;
    private String currencyName;
    public Currency(int id, String currencyName){
        this.id = id;
        this.currencyName = currencyName;
    }
    public int getId(){
        return id;
    }
    public String getCurrencyName(){
        return currencyName;
    }
    public String setCurrencyName(){
        return currencyName;
    }
    @Override
    public String toString(){
        return "currency:\n" +
                "id= " + id + "\n" +
                "currency: " + currencyName;
    }
}
