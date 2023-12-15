package com.wallet.DAO;
import com.wallet.Utils.CrudOperations;
import com.wallet.entities.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class AccountDAO implements CrudOperations<Account> {
    private final Connection connection;
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
        if (account.getAccountId() == null) {
            String insertQuery = "INSERT INTO account (accountName, balance, currencyId, type) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                insertStatement.setString(1, account.getAccountName());
                insertStatement.setDouble(2, account.getBalance());
                insertStatement.setString(3, account.getCurrency().getCurrencyId());
                insertStatement.setString(4, String.valueOf(account.getType()));
                int rowsAffected = insertStatement.executeUpdate();
                if (rowsAffected > 0) {
                    ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        String accountId = generatedKeys.getString(1);
                        account.setAccountId(accountId);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String updateQuery = "UPDATE account SET accountName = ?, balance = ?, currencyId = ?, type = ? WHERE accountId = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setString(1, account.getAccountName());
                updateStatement.setDouble(2, account.getBalance());
                updateStatement.setString(3, account.getCurrency().getCurrencyId());
                updateStatement.setString(4, String.valueOf(account.getType()));
                updateStatement.setString(5, account.getAccountId());
                int rowsAffected = updateStatement.executeUpdate();
                if (rowsAffected <= 0) {
                    System.out.println("wrong turn");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return account;
    }
    @Override
    public Account delete(Account toDelete) {
        String query = "DELETE FROM account WHERE accountId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, toDelete.getAccountId());
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
    public List<Transaction> getTransactionsForAccount(String accountId) {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM transaction WHERE accountId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, accountId);
            ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String transactionId = resultSet.getString("transactionId");
            String label = resultSet.getString("label");
            double amount = resultSet.getDouble("amount");
            Date transactionDateTime = resultSet.getTimestamp("transactionDateTime");
          
            String categoryId = resultSet.getString("categoryId");
            TransactionCategory category = new TransactionCategory();
            category.setIdCategory(categoryId);
            Transaction transaction = new Transaction(transactionId, label, amount, transactionDateTime, category);
          
            transactions.add(transaction);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
    private Account convertToAccount(ResultSet resultSet) throws SQLException {
        String accountId = resultSet.getString("accountId");
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
