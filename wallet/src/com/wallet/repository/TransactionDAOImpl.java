package com.wallet.repository;

import com.wallet.entities.Count;
import com.wallet.entities.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAO{
    private final Connection connection;

    public TransactionDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Transaction e) {
        String sql = "INSERT INTO count(typeTransaction, dateTransaction, amount, count) values (?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,e.getTypeTransaction());
            statement.setDate(2, Date.valueOf(e.getDateTransaction()));
            statement.setDouble(3,e.getAmount());
            statement.setInt(4,e.getCountId());

            statement.executeUpdate();
            System.out.println("insertion effectuer avec succes");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Transaction> findAll() {
        List<Transaction> Transaction = new ArrayList<>();
        String sql = "SELECT * FROM transaction";

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                Transaction.add(new Transaction(
                        resultSet.getInt("idTransaction"),
                        resultSet.getString("typeTransaction"),
                        resultSet.getDate("dateTransaction").toLocalDate(),
                        resultSet.getDouble("amount"),
                        resultSet.getInt("idCount")

                ));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Transaction;
    }

    @Override
    public Transaction findById(int id) {
        String sql = "SELECT * FROM transaction WHERE idTransaction = ? ";

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                return new Transaction(
                        resultSet.getInt("idTransaction"),
                        resultSet.getString("typeTransaction"),
                        resultSet.getDate("dateTransaction").toLocalDate(),
                        resultSet.getDouble("amount"),
                        resultSet.getInt("idCount")
                );
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Transaction e) {
        String sql = "UPDATE transaction SET typeTransaction = ?, dateTransaction = ?, amount = ?, count = ? WHERE idTransaction = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,e.getTypeTransaction());
            statement.setString(2, String.valueOf(e.getDateTransaction()));
            statement.setDouble(3,e.getAmount());
            statement.setInt(4,e.getCountId());

        } catch (SQLException ex) {
            throw new RuntimeException("Erreur lors de la mis à jour");
        }
    }

    @Override
    public Transaction delete(int id) {
        Transaction Transactiondel = this.findById(id);
        if(Transactiondel == null)
            return  null;
        String sql = "DELETE FROM transaction WHERE idTransaction = " + id;
        try{
            connection.prepareStatement(sql).executeUpdate();
            return Transactiondel;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
