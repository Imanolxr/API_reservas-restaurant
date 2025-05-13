package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.repository.reservation;

import com.SistemaReservas.Reservas_micMoc.domain.exception.NotfoundException;
import com.SistemaReservas.Reservas_micMoc.domain.model.Reservation;
import com.SistemaReservas.Reservas_micMoc.domain.port.output.ReservationRepositoryPort;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity.ReservationEntity;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity.TableEntity;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.reservation.ReservationEntityMapper;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.reservation.NewReservationDTOMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReservationRepositoryAdapter implements ReservationRepositoryPort {

    private final IReservationRepositoryAdapter reservationRepo;
    private final ReservationEntityMapper reservationEntityMapper;

    public ReservationRepositoryAdapter(IReservationRepositoryAdapter reservationRepo, NewReservationDTOMapper newReservationDTOMapper, ReservationEntityMapper reservationEntityMapper) {
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
    public boolean existsOverLappingReservation(List<TableEntity> tables, LocalDate requestedDate, LocalDateTime requestedTime, LocalDateTime endTime) {
        return reservationRepo.existsOverlappingReservation(tables, requestedDate, requestedTime, endTime);
    }

    @Override
    public void deleteReservation(Long reservationId) {
        reservationRepo.deleteById(reservationId);
    }

    @Override
    public boolean existsById(Long reservationId) {
        return reservationRepo.existsById(reservationId);
    }


}
