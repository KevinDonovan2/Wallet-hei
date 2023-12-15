package com.wallet.entities;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionCategory {
    private String idCategory;
    private String categoryName;
    private TransactionType transactionType;
}
