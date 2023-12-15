package com.wallet.Utils;

import com.wallet.entities.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionCrudOperations extends CrudOperations<Transaction> {
    List<Transaction> findByAccountIdAndDateRange(int accountId, Date startDate, Date endDate);
}

