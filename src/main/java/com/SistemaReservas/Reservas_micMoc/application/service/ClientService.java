package com.SistemaReservas.Reservas_micMoc.application.service;

import com.SistemaReservas.Reservas_micMoc.domain.exception.ClientNotFoundException;
import com.SistemaReservas.Reservas_micMoc.domain.model.Client;
import com.SistemaReservas.Reservas_micMoc.domain.port.input.ClientServicePort;
import com.SistemaReservas.Reservas_micMoc.domain.port.output.ClientRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements ClientServicePort {
    private final ClientRepositoryPort clientRepositoryPort;

    public ClientService(ClientRepositoryPort clientRepositoryPort) {
        this.clientRepositoryPort = clientRepositoryPort;

    }

    @Override
    public Client updateClient(Long id, Client updatedClient) {
        Client existing = clientRepositoryPort.findById(id).orElseThrow(() -> new ClientNotFoundException("Cliente no encontrado con el id: " + id));

        updatedClient.setId(existing.getId());
        clientRepositoryPort.saveClient(updatedClient);
        return updatedClient;
    }

    @Override
    public Client createClient(Client newClient){
        return clientRepositoryPort.saveClient(newClient);

    }
}
