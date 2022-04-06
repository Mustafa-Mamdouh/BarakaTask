package com.baraka.simple.bank.service;

import com.baraka.simple.bank.dtos.InternationalTransferTransaction;
import com.baraka.simple.bank.dtos.LocalTransferTransaction;
import com.baraka.simple.bank.dtos.SelfTransactionDto;
import com.baraka.simple.bank.exceptions.ApplicationException;
import com.baraka.simple.bank.models.Account;
import com.baraka.simple.bank.models.Transaction;
import com.baraka.simple.bank.models.TransactionType;
import com.baraka.simple.bank.repos.AccountRepo;
import com.baraka.simple.bank.repos.TransactionRepo;
import com.baraka.simple.bank.utils.AccountUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepo transactionRepo;
    private final AccountRepo accountRepo;

    public Transaction depositMoney(SelfTransactionDto selfTransactionDto) {
        Account account = accountRepo.findById(selfTransactionDto.getAccountId()).orElseThrow(AccountUtils.notFound());
        depositMoney(account, selfTransactionDto.getAmount());
        return transactionRepo.save(createTransaction(selfTransactionDto.getAmount(), account, null, TransactionType.SELF));
    }

    private void depositMoney(Account account, Double amount) {
        account.setBalance(account.getBalance() + amount);
        accountRepo.save(account);
    }


    public Iterable<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public Transaction withdrawMoney(SelfTransactionDto selfTransactionDto) {
        Account account = accountRepo.findById(selfTransactionDto.getAccountId()).orElseThrow(AccountUtils.notFound());
        withdrawFromAccount(account, selfTransactionDto.getAmount());
        return transactionRepo.save(createTransaction(selfTransactionDto.getAmount(), account, null, TransactionType.SELF));
    }

    private void withdrawFromAccount(Account account, Double amount) {
        if (account.getBalance() < amount) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST.value(), "Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepo.save(account);
    }

    public Transaction localTransfer(LocalTransferTransaction localTransferTransaction) {
        Account from = accountRepo.findById(localTransferTransaction.getFromAccountId()).orElseThrow(AccountUtils.notFound());
        Account to = accountRepo.findById(localTransferTransaction.getToAccountId()).orElseThrow(AccountUtils.notFound());
        if (from.getBalance() < localTransferTransaction.getAmount()) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST.value(), "Insufficient balance");
        }
        withdrawFromAccount(from, localTransferTransaction.getAmount());
        depositMoney(to, localTransferTransaction.getAmount());
        return transactionRepo.save(createTransaction(localTransferTransaction.getAmount(), from, to, TransactionType.INTERNAL));
    }

    private Transaction createTransaction(Double amount, Account from, Account to, TransactionType type) {
        return Transaction.builder().amount(amount).from(from).to(to).timestamp(LocalDateTime.now()).type(type).build();
    }

    public Transaction internationalTransfer(InternationalTransferTransaction internationalTransferTransaction) {
        Account from = accountRepo.findByIban(internationalTransferTransaction.getFromAccountIban()).orElseThrow(AccountUtils.notFound());
        Account to = accountRepo.findByIban(internationalTransferTransaction.getToAccountIban()).orElseThrow(AccountUtils.notFound());
        if (from.getBalance() < internationalTransferTransaction.getAmount()) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST.value(), "Insufficient balance");
        }
        withdrawFromAccount(from, internationalTransferTransaction.getAmount());
        depositMoney(to, internationalTransferTransaction.getAmount());
        return transactionRepo.save(createTransaction(internationalTransferTransaction.getAmount(), from, to, TransactionType.EXTERNAL));

    }
}
