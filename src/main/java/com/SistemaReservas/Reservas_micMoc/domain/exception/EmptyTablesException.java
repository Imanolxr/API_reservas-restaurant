package com.SistemaReservas.Reservas_micMoc.domain.exception;

public class EmptyTablesException extends RuntimeException {
    public EmptyTablesException(String message) {
        super(message);
    }
}
