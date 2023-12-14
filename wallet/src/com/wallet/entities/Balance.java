package com.wallet.entities;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Balance {
    private String balanceId;
    private BigDecimal amount;
    private LocalDate lastDateUpdate;
}
