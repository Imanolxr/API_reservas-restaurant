package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.reservation;

import com.SistemaReservas.Reservas_micMoc.domain.model.Client;
import com.SistemaReservas.Reservas_micMoc.domain.model.Reservation;
import com.SistemaReservas.Reservas_micMoc.domain.model.Table;
import com.SistemaReservas.Reservas_micMoc.domain.port.output.ClientRepositoryPort;
import com.SistemaReservas.Reservas_micMoc.domain.port.output.TableRepositoryPort;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.client.ClientMapper;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.table.TableMapper;
import com.SistemaReservas.Reservas_micMoc.infraestructure.rest.dto.ClientDTO;
import com.SistemaReservas.Reservas_micMoc.infraestructure.rest.dto.ReservationResponseDTO;
import com.SistemaReservas.Reservas_micMoc.infraestructure.rest.dto.TableDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReservationResponseDTOMapper {

    private final ClientMapper clientMapper;
    private final TableMapper tableMapper;


    public ReservationResponseDTOMapper(ClientMapper clientMapper, TableMapper tableMapper) {
        this.clientMapper = clientMapper;
        this.tableMapper = tableMapper;

    }

    public ReservationResponseDTO toDTO(Reservation model){

        ClientDTO clientDTO;

        clientDTO = clientMapper.toDto(model.getClientInReservation());
        List<TableDTO> tablesDTO = model.getTableInReservation().stream()
                .map(tableMapper::toDto)
                .toList();

        return new ReservationResponseDTO(
                model.getId(),
                clientDTO,
                tablesDTO,
                model.getNumberOfPeople(),
                model.getDate(),
                model.getTime()
        );
    }
}
