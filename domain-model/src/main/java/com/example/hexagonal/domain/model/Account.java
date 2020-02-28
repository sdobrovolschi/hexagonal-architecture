package com.example.hexagonal.domain.model;

import io.vavr.control.Either;

import java.math.BigDecimal;
import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Right;

public final class Account {

    private final AccountId id;
    private final Money money;

    private Account(AccountId id, Money money) {
        this.id = id;
        this.money = money;
    }

    public static Either<AccountOpeningFailed, Account> open(AccountId accountId, Money money) {
        Objects.requireNonNull(accountId, "accountId must not be null.");
        Objects.requireNonNull(money, "money must not be null.");

        if (money.getAmount().compareTo(BigDecimal.valueOf(1000)) >= 0) {
            return Right(new Account(accountId, money));
        }
        return Left(new AccountOpeningFailed("Non-compliant minimum balance"));
    }

    public void credit(Money money) {

    }

    public AccountId getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
