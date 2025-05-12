package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.repository.reservation;

import com.SistemaReservas.Reservas_micMoc.domain.model.Reservation;
import com.SistemaReservas.Reservas_micMoc.domain.port.output.ReservationRepositoryPort;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity.ReservationEntity;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.reservation.ReservationEntityMapper;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.reservation.ReservationMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReservationRepositoryAdapter implements ReservationRepositoryPort {

    private final IReservationRepositoryAdapter reservationRepo;
    private final ReservationEntityMapper reservationEntityMapper;

    public ReservationRepositoryAdapter(IReservationRepositoryAdapter reservationRepo, ReservationMapper reservationMapper, ReservationEntityMapper reservationEntityMapper) {
        this.reservationRepo = reservationRepo;
        this.reservationEntityMapper = reservationEntityMapper;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        ReservationEntity entity = reservationEntityMapper.toEntity(reservation);
        ReservationEntity savedEntity = reservationRepo.save(entity);
        return reservationEntityMapper.toModel(savedEntity);
    }

    @Override
    public boolean existsOverLappingReservation(List<Long> tableIds, LocalDate requestedDate, LocalDateTime requestedTime, LocalDateTime endTime) {
        return reservationRepo.existsOverlappingReservation(tableIds, requestedDate, requestedTime, endTime);
    }


}
