package com.SistemaReservas.Reservas_micMoc.domain.port.output;

import com.SistemaReservas.Reservas_micMoc.domain.model.Client;

import java.util.Optional;


public interface ClientRepositoryPort {

   Optional<Client>  findById(Long clientId);
   Client saveClient(Client client);
   boolean existsById(Long clientId);

}
