package com.example.hexagonal.adapter.persistence;

import com.example.hexagonal.domain.model.Account;
import com.example.hexagonal.domain.model.AccountId;
import com.example.hexagonal.domain.model.Accounts;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public final class InMemoryAccountRepository implements Accounts {

    private final Map<AccountId, Account> accounts = new ConcurrentHashMap<>();

    @Override
    public void add(Account account) {
        accounts.put(account.getId(), account);
    }

    @Override
    public Optional<Account> find(AccountId accountId) {
        return Optional.ofNullable(accounts.get(accountId));
    }
}
