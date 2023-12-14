package com.wallet.DAO;
import com.wallet.Utils.CrudOperations;
import com.wallet.entities.Balance;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class BalanceDAO implements CrudOperations<Balance> {
    private Connection connection;
    public BalanceDAO(Connection connection) {
        this.connection = connection;
    }
    @Override
    public List<Balance> findAll() {
        List<Balance> listBalance = new ArrayList<>();
        String query = "SELECT * FROM balance";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                listBalance.add(convertToBalance(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBalance;
    }
    @Override
    public List<Balance> saveAll(List<Balance> toSave) {
        String query = "INSERT INTO balance (amount, lastDateUpdate) VALUES (?, ?)";
        List<Balance> savedBalance = new ArrayList<>();
        try {
            for (Balance balance : toSave) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, balance.getBalanceId());
                    preparedStatement.setBigDecimal(2, balance.getAmount());
                    preparedStatement.setObject(3, balance.getLastDateUpdate());
                    int result = preparedStatement.executeUpdate();
                    if (result > 0) {
                        savedBalance.add(balance);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return savedBalance;
    }
    @Override
    public Balance save(Balance balance) {
        if (balance.getBalanceId() == null) {
            String insertQuery = "INSERT INTO balance (amount, lastDateUpdate) VALUES (?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                insertStatement.setBigDecimal(1, balance.getAmount());
                insertStatement.setObject(2, balance.getLastDateUpdate());
                int rowsAffected = insertStatement.executeUpdate();
                if (rowsAffected > 0) {
                    ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        String balanceId = generatedKeys.getString(1);
                        balance.setBalanceId(balanceId);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            String updateQuery = "UPDATE balance SET amount = ?, lastDateUpdate = ? WHERE balanceId = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setBigDecimal(1, balance.getAmount());
                updateStatement.setObject(2, balance.getLastDateUpdate());
                updateStatement.setString(3, balance.getBalanceId());

                int rowsAffected = updateStatement.executeUpdate();
                if (rowsAffected <= 0) {
                    System.out.println("Update not performed for scale ID: " + balance.getBalanceId());
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return balance;
    }
    @Override
    public Balance delete(Balance toDelete) {
        String query = "DELETE FROM balance WHERE amount = ? AND lastDateUpdate = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setBigDecimal(1, toDelete.getAmount());
            preparedStatement.setObject(2, toDelete.getLastDateUpdate());
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                return toDelete;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Balance convertToBalance(ResultSet resultSet) throws SQLException {
        String balanceId = resultSet.getString("balanceId");
        BigDecimal amount = resultSet.getBigDecimal("amount");
        LocalDate lastDateUpdate = (LocalDate) resultSet.getObject("lastDateUpdate");
        return new Balance(balanceId, amount, lastDateUpdate);
    }
}

