package com.wallet.entities;
import java.time.LocalDateTime;

public class Transaction {
    private int transactionId;
    private String label;
    private double amount;
    private LocalDateTime transactionDateTime;
    private String transactionType; 

    public Transaction(int transactionId, String label, double amount, LocalDateTime transactionDateTime, String transactionType){
        this.transactionId = transactionId;
        this.label = label;
        this.transactionDateTime = transactionDateTime;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public int gettransactionId(){
        return transactionId;
    }
    public String getlabel(){
        return label;
    }
    public LocalDateTime gettransactionDateTime(){
        return transactionDateTime;
    }
    public double getAmount(){
        return amount;
    }
    public String gettransactionTypeId(){
        return transactionType;
    }

    public void setTransactionId(int transactionId){
        this.transactionId = transactionId;
    }
    public void setlabel(String label){
        this.label = label;
    }
    public void settransactionDateTime(LocalDateTime transactionDateTime){
        this.transactionDateTime = transactionDateTime;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
    public void setAccountType(String transactionType){
        this.transactionType = transactionType;
    }
  
    @Override
    public String toString(){
        return "transaction:\n" +
                "id transaction: " + transactionId + "\n" +
                "label: " + label + "\n" +
                "date of transaction: " + transactionDateTime + "\n" +
                " amount: " + amount + "\n" +
                "transaction type: " + transactionType;
    }
}
