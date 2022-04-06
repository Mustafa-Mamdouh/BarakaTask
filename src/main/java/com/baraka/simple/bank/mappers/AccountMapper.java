package com.baraka.simple.bank.mappers;

import com.baraka.simple.bank.dtos.AccountDto;
import com.baraka.simple.bank.models.Account;
import org.mapstruct.Mapper;

@Mapper
public interface AccountMapper {
    Account asEntity(AccountDto accountDto);
    AccountDto asDto(Account account);
}
