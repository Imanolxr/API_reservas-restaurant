package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.reservation;

import com.SistemaReservas.Reservas_micMoc.domain.model.Reservation;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity.ReservationEntity;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.client.ClientEntityMapper;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.table.TableEntityMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ClientEntityMapper.class, TableEntityMapper.class})
public interface ReservationEntityMapper {

    @Mapping(source = "client", target = "clientInReservation")
    @Mapping(source = "tables", target = "tableInReservation")
    Reservation toModel(ReservationEntity entity);

    @Mapping(source = "clientInReservation", target = "client")
    @Mapping(source = "tableInReservation", target = "tables")
    ReservationEntity toEntity(Reservation model);

    @AfterMapping
    default void validateClient(@MappingTarget ReservationEntity entity) {
        if (entity.getClient() == null) {
            throw new IllegalArgumentException("La reserva debe tener un cliente asociado");
        }
}
}
