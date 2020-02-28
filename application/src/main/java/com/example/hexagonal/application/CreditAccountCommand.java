package com.example.hexagonal.application;

import java.math.BigDecimal;
import java.util.UUID;

public final class CreditAccountCommand {

    private final UUID accountId;
    private final BigDecimal amount;

    public CreditAccountCommand(UUID accountId, BigDecimal amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
