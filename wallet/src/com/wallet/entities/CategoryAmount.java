package com.wallet.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class CategoryAmount {
    String category;
    BigDecimal amount;

}
