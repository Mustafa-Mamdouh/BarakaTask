package com.baraka.simple.bank.dtos;

import com.baraka.simple.bank.dtos.validations.AccountGroups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String id;
    @NotNull(message = "ssn can not be null")
    @NotEmpty(message = "ssn can not be empty")
    private String ssn;
    @NotNull(message = "name can not be null")
    @NotEmpty(message = "name can not be empty")
    private String name;
    @Min(value = 0)
    private double balance;
    private String iban;
}
