package com.wallet.entities;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Balance {
    private BigDecimal amount;
    private LocalDate lastDateUpdate;
    
    public Balance(double amount, LocalDate lastDateUpdate){
        this.amount = BigDecimal.valueOf(amount);
        this.lastDateUpdate = lastDateUpdate;
    }

    public BigDecimal getAmount(){
        return amount;
    }
    public LocalDate getLastDateUpdate(){
        return lastDateUpdate;
    }
     public  BigDecimal setAmount(){
        return amount;
    }
    public LocalDate setLastDateUpdate(){
        return lastDateUpdate;
    }
    @Override
    public String toString(){
        return "The balance: \n" + "\n" +
                "Amount value: " + amount + "\n" +
                "last date update balance: " + lastDateUpdate;
    }
}
