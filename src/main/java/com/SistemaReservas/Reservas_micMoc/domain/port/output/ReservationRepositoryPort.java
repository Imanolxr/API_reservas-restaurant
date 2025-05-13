package com.SistemaReservas.Reservas_micMoc.domain.port.output;

import com.SistemaReservas.Reservas_micMoc.domain.model.Reservation;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity.TableEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepositoryPort {

    Reservation createReservation(Reservation reservation);

    boolean existsOverLappingReservation(List<TableEntity> tables, LocalDate requestedDate, LocalDateTime requestedTime, LocalDateTime endTime);

    void deleteReservation(Long reservationId);

    boolean existsById(Long reservationId);
}
