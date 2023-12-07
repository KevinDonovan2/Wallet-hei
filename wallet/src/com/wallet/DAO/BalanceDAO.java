package com.wallet.DAO;

import com.wallet.Utils.CrudOperations;
import com.wallet.entities.Balance;

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
                    preparedStatement.setDouble(1, balance.getAmount());
                    preparedStatement.setDate(2, java.sql.Date.valueOf(balance.getLastDateUpdate()));
    
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
        String query = "INSERT INTO balance (amount, lastDateUpdate) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, balance.getAmount());
            preparedStatement.setDate(2, java.sql.Date.valueOf(balance.getLastDateUpdate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return balance;
    }

    @Override
    public Balance delete(Balance toDelete) {
        String query = "DELETE FROM balance WHERE amount = ? AND lastDateUpdate = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, toDelete.getAmount());
            preparedStatement.setDate(2, java.sql.Date.valueOf(toDelete.getLastDateUpdate()));
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
        double amount = resultSet.getDouble("amount");
        LocalDate lastDateUpdate = resultSet.getDate("lastDateUpdate").toLocalDate();
        return new Balance(amount, lastDateUpdate);
    }
}

