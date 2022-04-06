package com.baraka.simple.bank.controller;

import com.baraka.simple.bank.dtos.AccountDto;
import com.baraka.simple.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/account")
@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public ResponseEntity getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity getByAccount(@PathVariable("id") String id) {
        return ResponseEntity.ok(accountService.getAccount(id));
    }

    @PostMapping
    public ResponseEntity addAccount(@Valid @RequestBody AccountDto accountDto) {
        return ResponseEntity.ok(accountService.createAccount(accountDto));
    }

    @DeleteMapping
    public ResponseEntity deleteAccount(@RequestBody AccountDto accountDto) {
        accountService.deleteAccount(accountDto);
        return ResponseEntity.ok().build();
    }
}
