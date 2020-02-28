package com.example.hexagonal.application;

import java.math.BigDecimal;

public final class OpenAccountCommand {

    private final BigDecimal amount;
    private final String currencyCode;

    public OpenAccountCommand(BigDecimal amount, String currencyCode) {
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }
}
