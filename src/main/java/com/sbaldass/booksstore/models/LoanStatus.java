package com.sbaldass.booksstore.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LoanStatus {
    EMPRESTADO("emprestado"),
    DEVOLVIDO("devolvido");

    private final String status;

    LoanStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }

    @JsonCreator
    public static LoanStatus fromStatus(String status) {
        for (LoanStatus s : LoanStatus.values()) {
            if (s.getStatus().equalsIgnoreCase(status)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + status);
    }
}
