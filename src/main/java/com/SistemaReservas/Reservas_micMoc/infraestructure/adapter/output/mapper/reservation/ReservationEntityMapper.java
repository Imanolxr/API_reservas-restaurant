package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.reservation;

import com.SistemaReservas.Reservas_micMoc.domain.model.Reservation;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity.ReservationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationEntityMapper {
    Reservation toModel(ReservationEntity entity);
    ReservationEntity toEntity(Reservation model);
}
