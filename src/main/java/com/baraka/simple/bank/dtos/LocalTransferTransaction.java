package com.baraka.simple.bank.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocalTransferTransaction {
    private String fromAccountId;
    private String toAccountId;
    private Double amount;
}
