package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.reservation;

import com.SistemaReservas.Reservas_micMoc.domain.model.Client;
import com.SistemaReservas.Reservas_micMoc.domain.model.Reservation;
import com.SistemaReservas.Reservas_micMoc.domain.model.Table;
import com.SistemaReservas.Reservas_micMoc.domain.port.output.ClientRepositoryPort;
import com.SistemaReservas.Reservas_micMoc.domain.port.output.TableRepositoryPort;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.client.ClientMapper;
import com.SistemaReservas.Reservas_micMoc.infraestructure.rest.dto.NewReservationDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NewReservationDTOMapper {

    private final ClientMapper clientMapper;
    private final ClientRepositoryPort clientRepositoryPort;
    private final TableRepositoryPort tableRepositoryPort;

    public NewReservationDTOMapper(ClientMapper clientMapper, ClientRepositoryPort clientRepositoryPort, TableRepositoryPort tableRepositoryPort) {
        this.clientMapper = clientMapper;
        this.clientRepositoryPort = clientRepositoryPort;
        this.tableRepositoryPort = tableRepositoryPort;
    }

    public Reservation toModel(NewReservationDTO dto){
        Long reservationId = null;
        Client client;
        if (dto.getClientId() != null){
            client = clientRepositoryPort.findById(dto.getClientId())
                    .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con id: " + dto.getClientId()));
        } else if (dto.getClient() != null) {
            client = clientMapper.toModel(dto.getClient());


        } else {
            throw new IllegalArgumentException("Se requiere un cliente existente o nuevo");
        }

        List<Table> tables = dto.getTableIds().stream()
                .map(id -> tableRepositoryPort.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Mesa no encontrada con id: " + id)))
                .toList();

        Reservation newReservation = new Reservation();
        newReservation.setClientInReservation(client);
        newReservation.setTableInReservation(tables);
        newReservation.setNumberOfPeople(dto.getNumberOfPeople());
        newReservation.setDate(dto.getDate());
        newReservation.setTime(dto.getTime());
        newReservation.setEndTime(dto.getTime().plusMinutes(90));
        newReservation.validate();

        return newReservation;


    }
}
