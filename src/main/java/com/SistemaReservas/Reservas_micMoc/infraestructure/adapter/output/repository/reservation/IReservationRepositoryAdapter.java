package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.repository.reservation;

import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReservationRepositoryAdapter extends JpaRepository<ReservationEntity, Long> {
}
