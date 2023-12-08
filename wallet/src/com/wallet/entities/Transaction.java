package com.wallet.entities;
import java.util.Date;

public class Transaction {
    private int transactionId;
    private String label;
    private double amount;
    private Date transactionDateTime;
    private TransactionType type;

    public Transaction(int transactionId, String label, double amount, Date transactionDateTime, TransactionType type){
        this.transactionId = transactionId;
        this.label = label;
        this.transactionDateTime = transactionDateTime;
        this.amount = amount;
        this.type = type;
    }

    public int getTransactionId(){
        return transactionId;
    }
    public String getlabel(){
        return label;
    }
    public Date getTransactionDateTime(){
        return transactionDateTime;
    }
    public double getAmount(){
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setTransactionId(int transactionId){
        this.transactionId = transactionId;
    }
    public void setlabel(String label){
        this.label = label;
    }
    public void settransactionDateTime(Date transactionDateTime){
        this.transactionDateTime = transactionDateTime;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
    public void setAccountType(String transactionType){
        this.type = type;
    }

    @Override
    public String toString(){
        return "transaction:\n" +
                "id transaction: " + transactionId + "\n" +
                "label: " + label + "\n" +
                "date of transaction: " + transactionDateTime + "\n" +
                " amount: " + amount + "\n" +
                "transaction type: " + type;
    }
}
