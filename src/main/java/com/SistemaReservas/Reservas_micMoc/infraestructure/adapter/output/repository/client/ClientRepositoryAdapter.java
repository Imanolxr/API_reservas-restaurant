package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.repository.client;

import com.SistemaReservas.Reservas_micMoc.domain.model.Client;
import com.SistemaReservas.Reservas_micMoc.domain.port.output.ClientRepositoryPort;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity.ClientEntity;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.client.ClientEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ClientRepositoryAdapter implements ClientRepositoryPort {
    private final IClientRepositoryAdapter clientRepo;
    private final ClientEntityMapper clientEntityMapper;

    public ClientRepositoryAdapter(IClientRepositoryAdapter clientRepo, ClientEntityMapper clientEntityMapper) {
        this.clientRepo = clientRepo;
        this.clientEntityMapper = clientEntityMapper;
    }


    @Override
    public Optional<Client> findById(Long clientId) {
        return clientRepo.findById(clientId)
                .map(clientEntityMapper::toModel);
    }

    @Override
    public void saveClient(Client client) {
        ClientEntity entity;
        entity = clientEntityMapper.toEntity(client);
        clientRepo.save(entity);

    }

}
