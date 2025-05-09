package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.client;


import com.SistemaReservas.Reservas_micMoc.domain.model.Client;
import com.SistemaReservas.Reservas_micMoc.infraestructure.rest.dto.ClientDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toModel(ClientDTO dto);
    ClientDTO toDto(Client model);



}
