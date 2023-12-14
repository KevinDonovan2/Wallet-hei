package com.wallet.entities;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    private String accountId;
    private String accountName;
    private double balance;
    private List<Transaction> transactions;
    private Currency currency;
    private AccountType type;
}
