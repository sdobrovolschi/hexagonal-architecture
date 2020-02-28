package com.example.hexagonal.domain.model;

import java.util.Objects;
import java.util.UUID;

public final class AccountId {

    private final UUID value;

    private AccountId(UUID value) {
        this.value = value;
    }

    public static AccountId valueOf(UUID value) {
        return new AccountId(value);
    }

    public static AccountId newIdentity() {
        return new AccountId(UUID.randomUUID());
    }

    public UUID toUUID() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountId accountId = (AccountId) o;
        return Objects.equals(value, accountId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
