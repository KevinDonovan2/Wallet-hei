package com.wallet.DAO;
import com.wallet.Utils.CrudOperations;
import com.wallet.entities.Currency;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyDAO implements CrudOperations<Currency> {
    private Connection connection;

    public CurrencyDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Currency> findAll() {
        List<Currency> listCurrency = new ArrayList<>();
        String query = "SELECT * FROM currency";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                listCurrency.add(convertToCurrency(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listCurrency;
    }

    @Override
    public List<Currency> saveAll(List<Currency> toSave){
        String query = "INSERT INTO currency (currencyId, currencyName, currencyCode) VALUES (?, ?, ?)";
        List<Currency> savedCurrency = new ArrayList<>();

        try {
            for (Currency currency : toSave) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, currency.getCurrencyId());
                    preparedStatement.setString(2, currency.getCurrencyName());
                    preparedStatement.setString(3, currency.getCurrencyCode());

                    int result = preparedStatement.executeUpdate();
                    if (result > 0) {
                        savedCurrency.add(currency);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return savedCurrency;
    }

    @Override
    public Currency save(Currency currency) {
        String query = "INSERT INTO currency (currencyId, currencyName, currencyCode) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, currency.getCurrencyId());
            preparedStatement.setString(2, currency.getCurrencyName());
            preparedStatement.setString(3, currency.getCurrencyCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return currency;
    }

    @Override
    public Currency delete(Currency toDelete) {
        String query = "DELETE FROM currency WHERE currencyId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, toDelete.getCurrencyId());
            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                return toDelete;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private Currency convertToCurrency(ResultSet resultSet) throws SQLException {
        int currencyId = resultSet.getInt("currencyId");
        String currencyName = resultSet.getString("currencyName");
        String currencyCode = resultSet.getString("currencyCode");
        return new Currency(currencyId, currencyName, currencyCode);
    }
}
