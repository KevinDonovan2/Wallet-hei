package com.wallet.entities;
public class Currency {
    private int idCurrency;
    private String currencyName;
    public Currency(int idCurrency, String currencyName){
        this.idCurrency = idCurrency;
        this.currencyName = currencyName;
    }

    public int getIdCurrency() {
        return idCurrency;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    @Override
    public String toString(){
        return "currency:\n" +
                "id= " + idCurrency + "\n" +
                "currency: " + currencyName;
    }
}
