package com.SistemaReservas.Reservas_micMoc.domain.exception;

public class ReservationConflictException extends RuntimeException {
    public ReservationConflictException(String message) {
        super(message);
    }
}
