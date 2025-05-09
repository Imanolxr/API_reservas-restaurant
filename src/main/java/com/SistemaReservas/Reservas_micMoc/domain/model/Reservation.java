package com.SistemaReservas.Reservas_micMoc.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Reservation {
    private Client clientInReservation;
    private List<Table> tableInReservation;
    private int numberOfPeople;
    private LocalDate date;
    private LocalDateTime time;

    public Reservation(Client clientInReservation, List<Table> tableInReservation, int numberOfPeople, LocalDate date, LocalDateTime time) {
        validateClient(clientInReservation);
        validateTables(tableInReservation);
        validateNumberOfPeople(numberOfPeople);
        validateDate(date);
        validateTime(time);

        this.clientInReservation = clientInReservation;
        this.tableInReservation = tableInReservation;
        this.numberOfPeople = numberOfPeople;
        this.date = date;
        this.time = time;
    }

    private void validateClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("La reserva debe tener un cliente asociado");
        }
    }

    private void validateTables(List<Table> tables) {
        if (tables == null || tables.isEmpty()) {
            throw new IllegalArgumentException("Debe asignarse al menos una mesa a la reserva");
        }
    }

    private void validateNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException("El número de personas debe ser mayor a 0");
        }
    }

    private void validateDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("La fecha de la reserva no puede ser nula");
        }
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de la reserva no puede ser en el pasado");
        }
    }

    private void validateTime(LocalDateTime time) {
        if (time == null) {
            throw new IllegalArgumentException("La hora de la reserva no puede ser nula");
        }
    }

    public Client getClientInReservation() {
        return clientInReservation;
    }

    public void setClientInReservation(Client clientInReservation) {
        validateClient(clientInReservation);
        this.clientInReservation = clientInReservation;
    }

    public List<Table> getTableInReservation() {
        return tableInReservation;
    }

    public void setTableInReservation(List<Table> tableInReservation) {
        validateTables(tableInReservation);
        this.tableInReservation = tableInReservation;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        validateNumberOfPeople(numberOfPeople);
        this.numberOfPeople = numberOfPeople;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        validateDate(date);
        this.date = date;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        validateTime(time);
        this.time = time;
    }
}
