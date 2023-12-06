package com.wallet.entities;
import java.time.LocalDate;

public class Balance {
    private double amount;
    private LocalDate lastDateUpdate;
    
    public Balance(double amount, LocalDate lastDateUpdate){
        this.amount = amount;
        this.lastDateUpdate = lastDateUpdate;
    }

    public  double getAmount(){
        return amount;
    }
    public LocalDate getLastDateUpdate(){
        return lastDateUpdate;
    }
     public  double setAmount(){
        return amount;
    }
    public LocalDate setLastDateUpdate(){
        return lastDateUpdate;
    }
    
    public String toString(){
        return "The balance: \n" + "\n" +
                "Amount value: " + amount + "\n" +
                "last date update balance: " + lastDateUpdate;
    }
}
