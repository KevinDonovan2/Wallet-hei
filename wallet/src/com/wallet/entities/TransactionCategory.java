package com.wallet.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCategory {
    private String idCategory;
    private String categoryName;
    private TransactionType transactionType;
}
