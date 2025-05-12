package com.SistemaReservas.Reservas_micMoc.domain.port.output;

import com.SistemaReservas.Reservas_micMoc.domain.model.Reservation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepositoryPort {

    Reservation createReservation(Reservation reservation);

    boolean existsOverLappingReservation(List<Long> tableIds, LocalDate requestedDate, LocalDateTime requestedTime, LocalDateTime endTime);
}
