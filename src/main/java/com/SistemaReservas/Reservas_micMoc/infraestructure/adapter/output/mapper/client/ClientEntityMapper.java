package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.client;


import com.SistemaReservas.Reservas_micMoc.domain.model.Client;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity.ClientEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientEntityMapper {
    Client toModel(ClientEntity entity);
    ClientEntity toEntity(Client model);
}
