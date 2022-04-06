package com.baraka.simple.bank.repos.impl;

import com.baraka.simple.bank.models.Transaction;
import com.baraka.simple.bank.repos.TransactionRepo;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class TransactionRepoImpl implements TransactionRepo {
    Map<String, Transaction> transactionMap = new LinkedHashMap<>();

    @Override
    public Transaction save(Transaction entity) {
        entity.setTxId(UUID.randomUUID().toString());
        transactionMap.put(entity.getTxId(), entity);
        return entity;
    }

    @Override
    public Iterable<Transaction> findAll() {
        return transactionMap.values();
    }

    @Override
    public Iterable<Transaction> findAllByAccountId(String id) {
        return transactionMap.values().stream().filter(tx -> id.equals(tx.getFrom().getId())).collect(Collectors.toList());
    }
}
