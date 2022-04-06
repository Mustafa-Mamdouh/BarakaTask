package com.baraka.simple.bank.repos.impl;

import com.baraka.simple.bank.exceptions.PersistenceLayerException;
import com.baraka.simple.bank.models.Account;
import com.baraka.simple.bank.repos.AccountRepo;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class AccountRepoImpl implements AccountRepo {
    Map<String, Account> currentAccounts = new LinkedHashMap<>();
    Map<String, String> ibanMap = new LinkedHashMap<>();

    public Account save(Account entity) {
        String id = entity.getId();
        if (entity.getId() == null) {
            id = initiateAccount(entity);
        }
        if (currentAccounts.containsKey(entity.getId())) {
            currentAccounts.replace(entity.getId(), entity);
        } else {
            currentAccounts.put(entity.getId(), entity);
        }
        return currentAccounts.get(id);
    }

    private String initiateAccount(Account entity) {
        String id;
        id = UUID.randomUUID().toString();
        entity.setId(id);
        entity.setCreationDate(LocalDateTime.now());
        entity.setIban(String.format("iban:%s", entity.getId()));
        ibanMap.put(entity.getIban(), entity.getId());
        return id;
    }

    public Iterable<Account> saveAll(Iterable<Account> entities) {
        ArrayList<Account> savedObjects = new ArrayList<>();
        for (Account s : entities) {
            savedObjects.add(save(s));
        }
        return savedObjects;
    }

    public Optional<Account> findById(String s) {
        return currentAccounts.containsKey(s) ? Optional.of(currentAccounts.get(s)) : Optional.empty();
    }

    public Optional<Account> findByIban(String s) {
        return ibanMap.containsKey(s) ? Optional.of(currentAccounts.get(ibanMap.get(s))) : Optional.empty();
    }

    public boolean existsById(String s) {
        return currentAccounts.containsKey(s);
    }

    public Iterable<Account> findAll() {
        return currentAccounts.values().stream().filter(account -> !account.isDeleted()).collect(Collectors.toList());
    }

    public Iterable<Account> findAllById(Iterable<Account> strings) {
        ArrayList<Account> accounts = new ArrayList<>();
        for (Account id : strings) {
            Account account = currentAccounts.get(id);
            if (account == null || account.isDeleted()) {
                throw new PersistenceLayerException(String.format("Account not found for id %s", id));

            }
            accounts.add(account);
        }
        return accounts;
    }

    public long count() {
        return currentAccounts.size();
    }

    public void deleteById(String s) {
        if (StringUtils.isEmpty(s)) {
            throw new PersistenceLayerException("Null or empty ids not acceptable");
        }
        Account account = currentAccounts.get(s);
        if (account == null) {
            throw new PersistenceLayerException(String.format("Can not found account with id: %s", s));
        }
        account.setDeleted(true);
    }

    public void deleteAll() {
        currentAccounts.values().forEach(account -> account.setDeleted(true));
    }
}
