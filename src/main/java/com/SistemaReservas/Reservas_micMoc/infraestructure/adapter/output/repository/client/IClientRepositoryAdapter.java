package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.repository.client;

import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepositoryAdapter extends JpaRepository<ClientEntity, Long> {
}
