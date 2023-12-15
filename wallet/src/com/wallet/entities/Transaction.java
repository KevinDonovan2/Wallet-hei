package com.wallet.entities;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private String transactionId;
    private String label;
    private double amount;
    private Date transactionDateTime;
  
    private TransactionCategory category;
}
