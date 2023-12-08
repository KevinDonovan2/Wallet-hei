package com.wallet.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accountId;
    private String accountName;
    private double balance;
    private Date lastUpdateDate;
    private List<Transaction> transactions;
    private Currency currency;
    private AccountType type;

    public Account(int accountId, String accountName, double balance, Date lastUpdateDate, List<Transaction> transactions, Currency currency, AccountType type) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.balance = balance;
        this.lastUpdateDate = lastUpdateDate;
        this.transactions = transactions;
        this.currency = currency;
        this.type = type;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + accountId +
                ", name='" + accountName + '\'' +
                ", balance=" + balance +
                ", lastUpdateDate=" + lastUpdateDate +
                ", transactions=" + transactions +
                ", currency=" + currency +
                ", type=" + type +
                '}';
    }
    public Account executeTransaction(Transaction transaction) {
        if (getType() == AccountType.BANK || getBalance() >= transaction.getAmount()) {
            Transaction newTransaction = new Transaction(
                    transaction.getTransactionId(),
                    transaction.getlabel(),
                    transaction.getAmount(),
                    transaction.getTransactionDateTime(),
                    transaction.getType()
            );


            getTransactions().add(newTransaction);

            if (transaction.getType() == TransactionType.DEBIT) {
                updateBalance(-transaction.getAmount());
            } else {
                updateBalance(transaction.getAmount());
            }

            return new Account(
                    getAccountId(),
                    getAccountName(),
                    getBalance(),
                    getLastUpdateDate(),
                    getTransactions(),
                    getCurrency(),
                    getType()
            );
        } else {
            System.out.println("Solde insuffisant pour effectuer la transaction.");
            return this;
        }
    }
    private void updateBalance(double amount) {
        setBalance(getBalance() + amount);
    }
}
