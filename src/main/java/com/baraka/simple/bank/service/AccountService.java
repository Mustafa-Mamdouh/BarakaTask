package com.baraka.simple.bank.service;

import com.baraka.simple.bank.dtos.AccountDto;
import com.baraka.simple.bank.exceptions.ApplicationException;
import com.baraka.simple.bank.mappers.AccountMapper;
import com.baraka.simple.bank.models.Account;
import com.baraka.simple.bank.repos.AccountRepo;
import com.baraka.simple.bank.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepo accountRepo;
    private final AccountMapper mapper = Mappers.getMapper(AccountMapper.class);

    public Account createAccount(AccountDto createAccountDto) {
        return accountRepo.save(mapper.asEntity(createAccountDto));
    }

    public List<AccountDto> getAllAccounts() {
        List<AccountDto> result = new ArrayList<>();
        accountRepo.findAll().forEach(account -> result.add(mapper.asDto(account)));
        return result;
    }

    public void deleteAccount(AccountDto accountDto) {
        accountRepo.deleteById(accountDto.getId());
    }

    public AccountDto getAccount(String id) {
        return mapper.asDto(accountRepo.findById(id).orElseThrow(AccountUtils.notFound()));

    }
}
