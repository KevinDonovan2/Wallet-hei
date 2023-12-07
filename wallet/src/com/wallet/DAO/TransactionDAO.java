package com.wallet.DAO;

import com.wallet.Utils.CrudOperations;
import com.wallet.entities.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO implements CrudOperations<Transaction> {
    private Connection connection;

    public TransactionDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Transaction> findAll() {
        List<Transaction> listTransaction = new ArrayList<>();
        String query = "SELECT * FROM transactions"; 

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                listTransaction.add(convertToTransaction(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listTransaction;
    }

    @Override
	public List<Transaction> saveAll(List<Transaction> toSave) {
	    String query = "INSERT INTO transactions (transactionId, label, amount, transactionDateTime, transactionType) " +
		           "VALUES (?, ?, ?, ?, ?)";
	    List<Transaction> savedTransactions = new ArrayList<>();

	    try {
		for (Transaction transaction : toSave) {
		    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		        preparedStatement.setInt(1, transaction.getTransactionId());
		        preparedStatement.setString(2, transaction.getlabel());
		        preparedStatement.setDouble(3, transaction.getAmount());
                preparedStatement.setObject(4, transaction.getTransactionDateTime());
		        preparedStatement.setString(5, transaction.getTransactionTypeId());

		        int result = preparedStatement.executeUpdate();
		        if (result > 0) {
		            savedTransactions.add(transaction);
		        }
		    }
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }

	    return savedTransactions;
	}
    @Override
	public Transaction save(Transaction toSave) {
	    String query = "INSERT INTO transactions (transactionId, label, amount, transactionDateTime, transactionType) " +
		           "VALUES (?, ?, ?, ?, ?)";

	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		preparedStatement.setInt(1, toSave.getTransactionId());
		preparedStatement.setString(2, toSave.getlabel());
		preparedStatement.setDouble(3, toSave.getAmount());
		preparedStatement.setObject(4, toSave.getTransactionDateTime());
		preparedStatement.setString(5, toSave.getTransactionTypeId());

		int result = preparedStatement.executeUpdate();
		if (result > 0) {
		    return toSave;
		}
	    } catch (SQLException e) {
		e.printStackTrace();
	    }

	    return null;
	}

    @Override
	public Transaction update(Transaction toUpdate) {
	    String query = "UPDATE transactions SET label = ?, amount = ?, transactionDateTime = ?, transactionType = ? " +
		           "WHERE transactionId = ?";

	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		preparedStatement.setString(1, toUpdate.getlabel());
		preparedStatement.setDouble(2, toUpdate.getAmount());
		preparedStatement.setObject(3, toUpdate.getTransactionDateTime());
		preparedStatement.setString(4, toUpdate.getTransactionTypeId());
		preparedStatement.setInt(5, toUpdate.getTransactionId());

		int result = preparedStatement.executeUpdate();
		if (result > 0) {
		    System.out.println("Transaction updated successfully.");
		} else {
		    System.out.println("No transaction found for the given ID.");
		}
	    } catch (SQLException e) {
		e.printStackTrace();
		
	    }
        return toUpdate;
	}
    @Override
	public Transaction delete(Transaction toDelete) {
	    String query = "DELETE FROM transactions WHERE transactionId = ?";

	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		preparedStatement.setInt(1, toDelete.getTransactionId());

		int result = preparedStatement.executeUpdate();
		if (result > 0) {
		    System.out.println("Transaction deleted successfully.");
		} else {
		    System.out.println("No transaction found for the given ID.");
		}
	    } catch (SQLException e) {
		e.printStackTrace();
		
	    }
        return toDelete;
	}

    private Transaction convertToTransaction(ResultSet resultSet) throws SQLException {
        int transactionId = resultSet.getInt("transactionId");
        String label = resultSet.getString("label");
        double amount = resultSet.getDouble("amount");
        LocalDateTime transactionDateTime = resultSet.getObject("transactionDateTime", LocalDateTime.class);
        String transactionType = resultSet.getString("transactionType");

        return new Transaction(transactionId, label, amount, transactionDateTime, transactionType);
    }
}

