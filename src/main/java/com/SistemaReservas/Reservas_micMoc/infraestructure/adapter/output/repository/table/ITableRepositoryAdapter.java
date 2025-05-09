package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.repository.table;

import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITableRepositoryAdapter extends JpaRepository<TableEntity, Long> {
}
