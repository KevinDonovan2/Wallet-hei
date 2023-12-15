package com.wallet.entities;

import com.wallet.Utils.TransactionCrudOperations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionCrudOperationsImpl implements TransactionCrudOperations {
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public List<Transaction> findAll() {
        return new ArrayList<>(transactions);
    }

    @Override
    public List<Transaction> saveAll(List<Transaction> toSave) {
        transactions.addAll(toSave);
        return toSave;
    }

    @Override
    public Transaction save(Transaction toSave) {
        transactions.add(toSave);
        return toSave;
    }

    @Override
    public Transaction delete(Transaction toDelete) {
        transactions.remove(toDelete);
        return toDelete;
    }

    @Override
    public List<Transaction> findByAccountIdAndDateRange(int accountId, Date startDate, Date endDate) {
        List<Transaction> result = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getAccountId().getId() == accountId &&
                    transaction.getTransactionDateTime().after(startDate) &&
                    transaction.getTransactionDateTime().before(endDate)) {
                result.add(transaction);
            }
        }

        return result;
    }
}

