package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.repository.table;

import com.SistemaReservas.Reservas_micMoc.domain.model.Table;
import com.SistemaReservas.Reservas_micMoc.domain.port.output.TableRepositoryPort;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity.TableEntity;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.table.TableEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TableRepositoryAdapter implements TableRepositoryPort {
    private final ITableRepositoryAdapter tableRepo;
    private final TableEntityMapper tableMapper;

    public TableRepositoryAdapter(ITableRepositoryAdapter tableRepo, TableEntityMapper tableMapper) {
        this.tableRepo = tableRepo;
        this.tableMapper = tableMapper;
    }


    @Override
    public Optional<Table> findById(Long id) {
        return tableRepo.findById(id).map(tableMapper::toModel);
    }

    @Override
    public Long countTables() {
        return tableRepo.count();

    }

    @Override
    public Table saveNewTable(Table table) {

        TableEntity entity = tableMapper.toEntity(table);
        tableRepo.save(entity);
        TableEntity savedEntity = tableRepo.save(entity);
        return tableMapper.toModel(savedEntity);
    }
}
