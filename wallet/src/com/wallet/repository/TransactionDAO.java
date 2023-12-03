package com.wallet.repository;

import com.wallet.entities.Count;
import com.wallet.entities.Transaction;

import java.util.List;

public interface TransactionDAO {
    void insert(Transaction e);
    List<Transaction> findAll();
    Transaction findById(int id);
    void update (Transaction e);
    Transaction delete (int id);
}
