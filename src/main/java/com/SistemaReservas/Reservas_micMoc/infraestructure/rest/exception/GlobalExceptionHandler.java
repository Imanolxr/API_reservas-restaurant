package com.SistemaReservas.Reservas_micMoc.infraestructure.rest.exception;


import com.SistemaReservas.Reservas_micMoc.domain.exception.ClientNotFoundException;
import com.SistemaReservas.Reservas_micMoc.domain.exception.EmptyTablesException;
import com.SistemaReservas.Reservas_micMoc.domain.exception.NotfoundException;
import com.SistemaReservas.Reservas_micMoc.domain.exception.ReservationConflictException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Map<String, Object>> buildResponse(String message, HttpStatus status, String path) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", path);
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(NotfoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NotfoundException ex, HttpServletRequest request){
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND, request.getRequestURI());

    }

    @ExceptionHandler(ReservationConflictException.class)
    public ResponseEntity<Map<String, Object>> handleConflictInReservation(ReservationConflictException ex, HttpServletRequest request){
        return buildResponse(ex.getMessage(), HttpStatus.CONFLICT, request.getRequestURI());
    }

    @ExceptionHandler(EmptyTablesException.class)
    public ResponseEntity<Map<String, Object>> handleEmptyTables(EmptyTablesException ex, HttpServletRequest request) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request.getRequestURI());
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Map<String, Object>>handleClientNotFound(ClientNotFoundException ex, HttpServletRequest request){
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, request.getRequestURI());
    }

}
