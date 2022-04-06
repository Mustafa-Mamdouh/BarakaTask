package com.baraka.simple.bank.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SelfTransactionDto {
    private String accountId;
    private Double amount;
}
