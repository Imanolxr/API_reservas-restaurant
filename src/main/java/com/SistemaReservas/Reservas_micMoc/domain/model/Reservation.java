package com.SistemaReservas.Reservas_micMoc.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Reservation {
    private Long id;
    private Client clientInReservation;
    private List<Table> tableInReservation;
    private int numberOfPeople;
    private LocalDate date;
    private LocalDateTime time;
    private LocalDateTime endTime;

    public Reservation() {
    }

    public Reservation(Client clientInReservation, List<Table> tableInReservation,
                       int numberOfPeople, LocalDate date, LocalDateTime time,
                       LocalDateTime endTime) {


        this.clientInReservation = clientInReservation;
        this.tableInReservation = tableInReservation;
        this.numberOfPeople = numberOfPeople;
        this.date = date;
        this.time = time;
        this.endTime = endTime;

    }
    public void validate() {
        validateClient(this.clientInReservation);
        validateTables(this.tableInReservation);
        validateNumberOfPeople(this.numberOfPeople);
        validateDate(this.date);
        validateTime(this.time);
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
        Set<Table> uniqueTables = new HashSet<>(tables);
        if (uniqueTables.size() != tables.size()) {
            throw new IllegalArgumentException("No se pueden asignar mesas duplicadas");
        }

    }

    private void validateNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException("El número de personas debe ser mayor a 0");
        }
        int totalCapacity = tableInReservation.stream()
                .mapToInt(Table::getCapacity)
                .sum();
        if(numberOfPeople > totalCapacity){
            throw new IllegalArgumentException("La capacidad total de las mesas no es suficiente para el número de personas de la reserva");
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
        LocalTime reservationTime = time.toLocalTime();
        LocalTime openingTime = LocalTime.of(19,0);
        LocalTime closingTime = LocalTime.of(22, 30);
        if (reservationTime.isBefore(openingTime) || reservationTime.isAfter(closingTime)){
            throw new IllegalArgumentException("La hora de la reserva debe estar entre las 19 y las 22 30");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClientInReservation() {
        return clientInReservation;
    }

    public void setClientInReservation(Client clientInReservation) {
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

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
