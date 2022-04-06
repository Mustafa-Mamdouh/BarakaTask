package com.baraka.simple.bank.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Account {
    private String id;
    private String iban;
    private String ssn;
    private double balance;
    private String name;
    private LocalDateTime creationDate;
    private LocalDateTime updateTime;
    boolean deleted = false;
}
