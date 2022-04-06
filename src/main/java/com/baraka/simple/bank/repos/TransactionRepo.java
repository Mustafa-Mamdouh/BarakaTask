package com.baraka.simple.bank.repos;

import com.baraka.simple.bank.models.Transaction;

public interface TransactionRepo {
    Transaction save(Transaction entity);

    Iterable<Transaction> findAll();

    Iterable<Transaction> findAllByAccountId(String id);
}
