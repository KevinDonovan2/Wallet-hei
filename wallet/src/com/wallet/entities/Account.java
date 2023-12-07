package com.wallet.entities;
import java.util.ArrayList;
import java.util.List;
public class Account {
    private int accountId;
    private String accountName;
    private Balance balance;
    private List<Transaction> transactionList;
    private Currency currency;
    private String accountType;
    
    public Account(int accountId, String accountName, Balance balance, Currency currency, String accountType) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.balance = balance;
        this.currency = currency;
        this.accountType = accountType;
        this.transactionList = new ArrayList<>();
    }

    public int getAccountId(){
        return accountId;
    }
    public String getAccountName(){
        return accountName;
    }
    public Balance getBalance(){
        return balance;
    }
    public List<Transaction> getTransactions() {
        return transactionList;
    }
    public Currency getCurrency(){
        return currency;
    }
    public String getAccountType(){
        return accountType;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public void setBalance(Balance balance) {
        this.balance = balance;
    }
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    public void setAccountType(String accountType){
        this.accountType = accountType;
    }
    public void addTransaction(Transaction transaction) {
        this.transactionList.add(transaction);
    }
    @Override
    public String toString(){
        return "Yout account: \n" + 
        "id account: " + accountId + "\n" +
        "account name: " + accountName + "\n" +
        "Your balance: " + balance.getAmount() + "\n" +
        "transaction: " + transactionList+ "\n" +
        "your currency count: " + currency + "\n" +
        "the account type: " + accountType;
    }
}