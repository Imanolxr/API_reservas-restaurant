package com.SistemaReservas.Reservas_micMoc.domain.port.output;

import com.SistemaReservas.Reservas_micMoc.domain.model.Table;

import java.util.Optional;

public interface TableRepositoryPort {


    Optional<Table> findById(Long id);

    Long countTables();

    Table saveNewTable(Table table);
}
