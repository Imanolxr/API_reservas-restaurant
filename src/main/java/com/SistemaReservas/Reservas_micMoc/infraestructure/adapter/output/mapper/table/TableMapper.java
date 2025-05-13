package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.table;

import com.SistemaReservas.Reservas_micMoc.domain.model.Table;
import com.SistemaReservas.Reservas_micMoc.infraestructure.rest.dto.TableDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TableMapper {

    Table toModel(TableDTO dto);
    TableDTO toDto(Table model);

}
