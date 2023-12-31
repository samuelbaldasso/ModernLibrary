package com.sbaldass.booksstore.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ReservationStatus {
    DISPONIVEL("disponivel"),
    RESERVADO("reservado");

    private final String status;

    ReservationStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }

    @JsonCreator
    public static ReservationStatus fromStatus(String status) {
        for (ReservationStatus s : ReservationStatus.values()) {
            if (s.getStatus().equalsIgnoreCase(status)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + status);
    }
}
