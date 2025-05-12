package com.SistemaReservas.Reservas_micMoc.domain.port.input;

import com.SistemaReservas.Reservas_micMoc.domain.model.Client;

import java.util.Optional;

public interface ClientServicePort {
    Client updateClient(Long id, Client updatedClient);

}
