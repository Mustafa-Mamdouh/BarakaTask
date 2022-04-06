package com.baraka.simple.bank.repos;

import com.baraka.simple.bank.models.Account;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepo {
    Account save(Account entity);

    Iterable<Account> saveAll(Iterable<Account> entities);

    Optional<Account> findById(String s);

    boolean existsById(String s);

    Iterable<Account> findAll();

    Iterable<Account> findAllById(Iterable<Account> strings);

    long count();

    void deleteById(String s);

    void deleteAll();

    Optional<Account> findByIban(String s);
}
