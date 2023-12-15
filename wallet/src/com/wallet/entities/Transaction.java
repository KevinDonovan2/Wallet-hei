package com.wallet.entities;
import java.util.Date;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    private String transactionId;
    private String label;
    private double amount;
    private Date transactionDateTime;
  
    private TransactionCategory category;
}
