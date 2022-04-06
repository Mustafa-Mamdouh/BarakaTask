package com.baraka.simple.bank.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Transaction {
    String txId;
    Account from;
    Account to;
    TransactionType type;
    Double amount;
    LocalDateTime timestamp;
}
