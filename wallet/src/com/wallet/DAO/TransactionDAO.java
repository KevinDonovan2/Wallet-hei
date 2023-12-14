package com.wallet.DAO;
import com.wallet.Utils.CrudOperations;
import com.wallet.entities.Transaction;
import com.wallet.entities.TransactionType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class TransactionDAO implements CrudOperations<Transaction> {
    private final Connection connection;
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
	    String query = "INSERT INTO transactions (transactionId, label, amount, transactionDateTime, type) " +
		           "VALUES (?, ?, ?, ?, ?)";
	    List<Transaction> savedTransactions = new ArrayList<>();
	    try {
		for (Transaction transaction : toSave) {
		    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		        preparedStatement.setString(1, transaction.getTransactionId());
		        preparedStatement.setString(2, transaction.getLabel());
		        preparedStatement.setDouble(3, transaction.getAmount());
                preparedStatement.setObject(4, transaction.getTransactionDateTime());
		        preparedStatement.setString(5, String.valueOf(transaction.getType()));
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
	public Transaction save(Transaction transaction) {
		if (transaction.getTransactionId() == null) {
			String insertQuery = "INSERT INTO transactions (label, amount, transactionDateTime, type) VALUES (?, ?, ?, ?)";
			try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
				insertStatement.setString(1, transaction.getLabel());
				insertStatement.setDouble(2, transaction.getAmount());
				insertStatement.setObject(3, transaction.getTransactionDateTime());
				insertStatement.setString(4, String.valueOf(transaction.getType()));
				int rowsAffected = insertStatement.executeUpdate();
				if (rowsAffected > 0) {
					ResultSet generatedKeys = insertStatement.getGeneratedKeys();
					if (generatedKeys.next()) {
						String transactionId = generatedKeys.getString(1);
						transaction.setTransactionId(transactionId);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			String updateQuery = "UPDATE transactions SET label = ?, amount = ?, transactionDateTime = ?, type = ? WHERE transactionId = ?";
			try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
				updateStatement.setString(1, transaction.getLabel());
				updateStatement.setDouble(2, transaction.getAmount());
				updateStatement.setObject(3, transaction.getTransactionDateTime());
				updateStatement.setString(4, String.valueOf(transaction.getType()));
				updateStatement.setString(5, transaction.getTransactionId());
				int rowsAffected = updateStatement.executeUpdate();
				if (rowsAffected <= 0) {
					System.out.println("Transaction ID has not been updated : " + transaction.getTransactionId());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return transaction;
	}
	@Override
	public Transaction delete(Transaction toDelete) {
	    String query = "DELETE FROM transactions WHERE transactionId = ?";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
		preparedStatement.setString(1, toDelete.getTransactionId());

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
        String transactionId = resultSet.getString("transactionId");
        String label = resultSet.getString("label");
        double amount = resultSet.getDouble("amount");
        Date transactionDateTime = resultSet.getObject("transactionDateTime", Date.class);
		TransactionType type = TransactionType.valueOf(resultSet.getString("type"));
        return new Transaction(transactionId, label, amount, transactionDateTime, type);
    }
}

