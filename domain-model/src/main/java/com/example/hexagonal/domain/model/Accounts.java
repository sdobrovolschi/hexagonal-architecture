package com.example.hexagonal.domain.model;

import java.util.Optional;

public interface Accounts {

    void add(Account account);

    Optional<Account> find(AccountId accountId);
}
