package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.table;

import com.SistemaReservas.Reservas_micMoc.domain.model.Table;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity.TableEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TableEntityMapper {
    Table toModel(TableEntity entity);
    TableEntity toEntity(Table model);
}
