package com.wallet.entities;
public class Account {
    private int accountId;
    private String accountName;
    private Balance balance;
    private String transactions;
    private Currency currency;
    private String accountType;
    
    public Account(int accountId, String accountName, Balance balance, String transactions, Currency currency, String accountType){
        this.accountId = accountId;
        this.accountName = accountName;
        this.balance = balance;
        this.transactions = transactions;
        this.currency = currency;
        this.accountType = accountType;
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
    public String getTransactions(){
        return transactions;
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
    public void setTransactions(String transactions) {
        this.transactions = transactions;
    }
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
    public void setAccountType(String accountType){
        this.accountType = accountType;
    }
    @Override
    public String toString(){
        return "Yout account: \n" + 
        "id account: " + accountId + "\n" +
        "account name: " + accountName + "\n" +
        "Your balance: " + balance + "\n" +
        "transaction: " + transactions + "\n" +
        "your currency count: " + currency + "\n" +
        "the account type: " + accountType;
    }
}