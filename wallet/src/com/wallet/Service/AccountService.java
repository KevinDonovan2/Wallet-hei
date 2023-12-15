package com.wallet.Service;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.wallet.entities.Account;
import com.wallet.entities.Transaction;
import com.wallet.entities.AccountType;
import com.wallet.entities.TransactionType;
public class AccountService {
    public Account executeTransaction(Account account, Transaction transaction) {
        if (account.getType() == AccountType.BANK || account.getBalance() >= transaction.getAmount()) {
            Transaction newTransaction = new Transaction(
                transaction.getTransactionId(),
                transaction.getLabel(),
                transaction.getAmount(),
                transaction.getTransactionDateTime(),

                transaction.getCategory()
            );
            account.getTransactions().add(newTransaction);

            if (transaction.getCategory().getTransactionType() == TransactionType.debit) {
     updateBalance(account, -transaction.getAmount());
            } else {
                updateBalance(account, transaction.getAmount());
            }
            return new Account(
                account.getAccountId(),
                account.getAccountName(),
                account.getBalance(),
                account.getTransactions(),
                account.getCurrency(),
                account.getType()
            );
        } else {
            System.out.println("Insufficient balance to complete transaction.");
            return account;
        }
    }
    private void updateBalance(Account account, double amount) {
        account.setBalance(account.getBalance() + amount);
    }
    public double getBalanceAtDateTime(Account account, Date dateTime) {
        double balance = 0.0;

        for (Transaction transaction : account.getTransactions()) {
            if (transaction.getTransactionDateTime().before(dateTime) || transaction.getTransactionDateTime().equals(dateTime)) {
              
                if (transaction.getCategory().getTransactionType() == TransactionType.credit) {
                    balance += transaction.getAmount();
                } else if (transaction.getCategory().getTransactionType() == TransactionType.debit) {
                    balance -= transaction.getAmount();
                }
            }
        }
        return balance;
    }
    public List<Double> getBalanceHistoryInDateTimeRange(Account account, Date startDateTime, Date endDateTime) {
        List<Double> balanceHistory = new ArrayList<>();
        double currentBalance = 0.0;
        for (Transaction transaction : account.getTransactions()) {
            if ((transaction.getTransactionDateTime().after(startDateTime) || transaction.getTransactionDateTime().equals(startDateTime))
                    && transaction.getTransactionDateTime().before(endDateTime)) {
                if (transaction.getCategory().getTransactionType() == TransactionType.credit) {
                    currentBalance += transaction.getAmount();
                } else if (transaction.getCategory().getTransactionType() == TransactionType.debit) {
                    currentBalance -= transaction.getAmount();
                }
                balanceHistory.add(currentBalance);
            }
        }
        return balanceHistory;
    }
}

