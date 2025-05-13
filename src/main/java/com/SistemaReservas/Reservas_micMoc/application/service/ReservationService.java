package com.SistemaReservas.Reservas_micMoc.application.service;

import com.SistemaReservas.Reservas_micMoc.domain.exception.ClientNotFoundException;
import com.SistemaReservas.Reservas_micMoc.domain.exception.EmptyTablesException;
import com.SistemaReservas.Reservas_micMoc.domain.exception.NotfoundException;
import com.SistemaReservas.Reservas_micMoc.domain.exception.ReservationConflictException;
import com.SistemaReservas.Reservas_micMoc.domain.model.Client;
import com.SistemaReservas.Reservas_micMoc.domain.model.Reservation;
import com.SistemaReservas.Reservas_micMoc.domain.port.input.ReservationServicePort;
import com.SistemaReservas.Reservas_micMoc.domain.port.output.ClientRepositoryPort;
import com.SistemaReservas.Reservas_micMoc.domain.port.output.ReservationRepositoryPort;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity.TableEntity;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.table.TableEntityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService implements ReservationServicePort {
    private final ReservationRepositoryPort reservationRepo;
    private final ClientRepositoryPort clientRepositoryPort;
    private final TableEntityMapper tableEntityMapper;
    private final ClientService clientService;

    public ReservationService(ReservationRepositoryPort reservationRepo, ClientRepositoryPort clientRepositoryPort, TableEntityMapper tableEntityMapper, ClientService clientService) {
        this.reservationRepo = reservationRepo;
        this.clientRepositoryPort = clientRepositoryPort;
        this.tableEntityMapper = tableEntityMapper;
        this.clientService = clientService;
    }


    @Transactional
    public Reservation createReservation(Reservation model) {
        if (model.getClientInReservation() == null){
            throw new ClientNotFoundException("La reserva debe tener un cliente asignado.");
        }
        Long clientId = model.getClientInReservation().getId();
        // Si el cliente no existe en la base de datos
        if (clientId == null){
            // Guarda al cliente primero para obtener su ID
            Client newClient = clientService.createClient(model.getClientInReservation());
            model.setClientInReservation(newClient);
            clientId = newClient.getId();  // Obtener el ID generado
        }
        if (model.getTableInReservation().isEmpty()){
            throw new EmptyTablesException("No se puede realizar una reserva si no hay mesas seleccionadas.");
        }

        List<TableEntity> tables = model.getTableInReservation().stream().map(tableEntityMapper::toEntity).toList();
        LocalDate requestedDate = model.getDate();
        LocalDateTime requestedTime = model.getTime();
        LocalDateTime endTime = model.getEndTime();

        // Verifica si hay reservas que se solapen
        if (reservationRepo.existsOverLappingReservation(tables, requestedDate, requestedTime, endTime)) {
            throw new ReservationConflictException("Ya existe una Reserva para la/s mesa/s en el horario seleccionado.");
        }

        // Finalmente crea la reserva
        return reservationRepo.createReservation(model);
    }

    public void deleteReservation(Long reservationId) {
        if (!reservationRepo.existsById(reservationId)){
            throw new NotfoundException("Reserva no encontrada con id: " + reservationId);
        }
        reservationRepo.deleteReservation(reservationId);
    }
}
