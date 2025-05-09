package com.SistemaReservas.Reservas_micMoc.domain.model;

import java.util.List;

public class Table {
    private Long id;
    private String tableNumber;
    private int capacity;
    private List<Reservation> reservations;

    public Table() {
    }

    public Table(Long id, String tableNumber, int capacity, List<Reservation> reservations) {

        if (tableNumber == null || tableNumber.isEmpty()) {
            throw new IllegalArgumentException("El número de mesa no puede ser vacío");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("La capacidad de la mesa debe ser mayor que cero");
        }

        this.id = id;
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        // Validación antes de asignar el valor
        if (tableNumber == null || tableNumber.isEmpty()) {
            throw new IllegalArgumentException("El número de mesa no puede ser vacío");
        }
        this.tableNumber = tableNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        // Validación antes de asignar el valor
        if (capacity <= 0) {
            throw new IllegalArgumentException("La capacidad de la mesa debe ser mayor que cero");
        }
        this.capacity = capacity;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}

