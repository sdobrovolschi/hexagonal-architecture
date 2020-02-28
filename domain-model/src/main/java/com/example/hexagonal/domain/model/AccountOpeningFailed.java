package com.example.hexagonal.domain.model;

public final class AccountOpeningFailed {

    private final String reason;

    AccountOpeningFailed(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
