package com.wallet.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    private String currencyId;
    private String currencyName;
    private String currencyCode;
}
