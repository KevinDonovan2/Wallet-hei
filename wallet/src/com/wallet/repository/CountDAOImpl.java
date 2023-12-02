package com.wallet.repository;

import com.wallet.entities.Count;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountDAOImpl implements CountDAO {
    private final Connection connection;

    public CountDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Count e) {
        String sql = "INSERT INTO count(bankName, userName, countNumber, accountType, currency, balance) values (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,e.getBankName());
            statement.setString(2,e.getUserName());
            statement.setString(3,e.getCountNumber());
            statement.setString(4,e.getAccountType());
            statement.setString(5, e.getCurrency());
            statement.setDouble(6, Double.parseDouble("balance"));

            statement.executeUpdate();
            System.out.println("insertion effectuer avec succes");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Count> findAll() {
        List<Count> Count = new ArrayList<>();
        String sql = "SELECT * FROM count";

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Count.add(new Count(
                        resultSet.getInt("idCount"),
                        resultSet.getString("bankName"),
                        resultSet.getString("userName"),
                        resultSet.getString("countNumber"),
                        resultSet.getString("accountType"),
                        resultSet.getCu(),
                        resultSet.getDouble("balance")

                ));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Count;
    }

    @Override
    public Count findById(int id) {
        String sql = "SELECT * FROM count WHERE idCount = ? ";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                return new Count(
                        resultSet.getInt("idCount"),
                        resultSet.getString("bankName"),
                        resultSet.getString("userName"),
                        resultSet.getString("countNumber"),
                        resultSet.getString("accountType"),
                        resultSet.getCurrency(),
                        resultSet.getDouble("balance")
                );
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Count e) {
        String sql = "UPDATE Count SET bankName = ?, userName = ?, countNumber = ?, accountType = ?, currency = ?, balance = ? WHERE idCount = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,e.getBankName());
            statement.setString(2,e.getUserName());
            statement.setString(3,e.getCountNumber());
            statement.setString(4,e.getAccountType());
            statement.setString(5, e.getCurrency());
            statement.setDouble(6, Double.parseDouble("balance"));
            statement.setInt(6,e.getIdCount());

        } catch (SQLException ex) {
            throw new RuntimeException("Erreur lors de la mis à jour");
        }
    }

    @Override
    public Count delete(int id) {
        Count countdel = this.findById(id);
        if(countdel == null)
            return  null;
        String sql = "DELETE FROM count WHERE idCount = " + id;
        try{
            connection.prepareStatement(sql).executeUpdate();
            return countdel;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
