package com.SistemaReservas.Reservas_micMoc.domain.port.input;

import com.SistemaReservas.Reservas_micMoc.domain.model.Reservation;

public interface ReservationServicePort {

    Reservation createReservation(Reservation reservation);
}
