package com.baraka.simple.bank.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InternationalTransferTransaction {
    private String fromAccountIban;
    private String toAccountIban;
    private Double amount;

}
