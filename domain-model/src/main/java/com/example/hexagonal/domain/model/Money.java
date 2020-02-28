package com.example.hexagonal.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

public final class Money { //shared Kernel??

    private final BigDecimal amount;
    private final Currency currency;

    private Money(BigDecimal amount, Currency currency) {
        Objects.requireNonNull(amount, "amount must not be null.");
        Objects.requireNonNull(currency, "currency must not be null");
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollars(BigDecimal amount) {
        return new Money(amount, Currency.USD);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return Objects.equals(amount, money.amount) &&
                currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }
}
