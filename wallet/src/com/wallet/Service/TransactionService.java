package com.wallet.Service;


import com.wallet.Utils.TransactionCrudOperations;
import com.wallet.entities.Transaction;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
public class TransactionService {

    private final TransactionCrudOperations transactionCrudOperations;
    public TransactionService(TransactionCrudOperations transactionCrudOperations) {
        this.transactionCrudOperations = transactionCrudOperations;

    }
    public Map<String, Double> calculateCategoryAmount(int accountId, Date startDate, Date endDate) {
        Map<String, Double> categoryAmounts = new HashMap<>();
        List<Transaction> transactions = transactionCrudOperations.findByAccountIdAndDateRange(accountId, startDate, endDate);
        for (Transaction transaction : transactions) {
            String categoryName = transaction.getTransactionCategory().getCategoryName();
            double amount = transaction.getAmount();
            categoryAmounts.put(categoryName, categoryAmounts.getOrDefault(categoryName, 0.0) + amount);
        }
        return categoryAmounts;
    }

    public Map<String, Double> sumAmountInOut(int accountId, Date startDate, Date endDate) {
        Map<String, Double> result = new HashMap<>();

        List<Transaction> creditTransactions = transactionCrudOperations.findByAccountIdAndDateRange(accountId, startDate, endDate);
        double totalCredit = creditTransactions.stream()
                .filter(t -> t.getTransactionCategory().getTransactionType().equals("credit"))
                .mapToDouble(Transaction::getAmount)
                .sum();
        result.put("totalCredit", totalCredit);

        List<Transaction> debitTransactions = transactionCrudOperations.findByAccountIdAndDateRange(accountId, startDate, endDate);
        double totalDebit = debitTransactions.stream()
                .filter(t -> t.getTransactionCategory().getTransactionType().equals("debit"))
                .mapToDouble(Transaction::getAmount)
                .sum();
        result.put("totalDebit", totalDebit);
        return result;

    }

}