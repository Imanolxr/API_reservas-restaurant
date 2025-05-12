package com.SistemaReservas.Reservas_micMoc.application.service;

import com.SistemaReservas.Reservas_micMoc.domain.model.Reservation;
import com.SistemaReservas.Reservas_micMoc.domain.model.Table;
import com.SistemaReservas.Reservas_micMoc.domain.port.input.ReservationServicePort;
import com.SistemaReservas.Reservas_micMoc.domain.port.output.ClientRepositoryPort;
import com.SistemaReservas.Reservas_micMoc.domain.port.output.ReservationRepositoryPort;
import com.SistemaReservas.Reservas_micMoc.domain.port.output.TableRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationService implements ReservationServicePort {
    private final ReservationRepositoryPort reservationRepo;
    private final ClientRepositoryPort clientRepositoryPort;
    private final TableRepositoryPort tableRepo;

    public ReservationService(ReservationRepositoryPort reservationRepo, ClientRepositoryPort clientRepositoryPort, TableRepositoryPort tableRepo) {
        this.reservationRepo = reservationRepo;
        this.clientRepositoryPort = clientRepositoryPort;
        this.tableRepo = tableRepo;
    }


    @Transactional
    public Reservation createReservation(Reservation model) {

        Long clientId = model.getClientInReservation().getId();
        if (clientRepositoryPort.findById(clientId).isEmpty()){
            clientRepositoryPort.saveClient(model.getClientInReservation());
        }
        List<Long> tableIds = model.getTableInReservation().stream().map(Table::getId).collect(Collectors.toList());
        LocalDate requestedDate = model.getDate();
        LocalDateTime requestedTime = model.getTime();
        LocalDateTime endTime = requestedTime.plusMinutes(90);
        if(reservationRepo.existsOverLappingReservation(tableIds,requestedDate, requestedTime, endTime)){
            throw new IllegalArgumentException("Una o más mesas ya estan reservadas para ese horario");
        }

        return reservationRepo.createReservation(model);

    }
}
