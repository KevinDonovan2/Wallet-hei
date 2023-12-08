package com.wallet.DAO;

import com.wallet.Utils.CrudOperations;
import com.wallet.entities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountDAO implements CrudOperations<Account> {
    private Connection connection;

    public AccountDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Account> findAll() {
        List<Account> listAccounts = new ArrayList<>();
        String query = "SELECT * FROM account";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                listAccounts.add(convertToAccount(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listAccounts;
    }

    @Override
    public List<Account> saveAll(List<Account> toSave) {
        return null;
    }

    @Override
    public Account save(Account account) {
        String query = "INSERT INTO account (accountId, accountName, balance, currencyId, type) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, account.getAccountId());
            preparedStatement.setString(2, account.getAccountName());
            preparedStatement.setDouble(3, account.getBalance());
            preparedStatement.setInt(4, account.getCurrency().getCurrencyId());
            preparedStatement.setString(5, String.valueOf(account.getType()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return account;
    }
    @Override
    public Account update(Account toUpdate){
        return null;
    }
    @Override
    public Account delete(Account toDelete) {
        String query = "DELETE FROM account WHERE accountId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, toDelete.getAccountId());
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                return toDelete;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    // Methode supplementaire pour recuperer la liste des transaction liée à un certain compte
    public List<Transaction> getTransactionsForAccount(int accountId) {
        List<Transaction> transactions = new ArrayList<>();

        String query = "SELECT * FROM transaction WHERE accountId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int transactionId = resultSet.getInt("transactionId");
            String label = resultSet.getString("label");
            double amount = resultSet.getDouble("amount");
            Date transactionDateTime = resultSet.getTimestamp("transactionDateTime");
            TransactionType type = TransactionType.valueOf(resultSet.getString("transactionType"));

            Transaction transaction = new Transaction(transactionId, label, amount, transactionDateTime, type);
            transactions.add(transaction);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }
    private Account convertToAccount(ResultSet resultSet) throws SQLException {
        int accountId = resultSet.getInt("accountId");
        String accountName = resultSet.getString("accountName");
        double balance = (double) resultSet.getObject("balance");
        List<Transaction> listTransaction = getTransactionsForAccount(accountId);
        Currency currency = (Currency) resultSet.getObject("idCurrencyCount");
        AccountType type = AccountType.valueOf(resultSet.getString("accountType"));


        Account account = new Account(accountId, accountName, balance, listTransaction, currency, type);
        account.getTransactions().addAll(listTransaction);

        return account;
    }

}
