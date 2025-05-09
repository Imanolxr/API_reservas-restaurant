package com.SistemaReservas.Reservas_micMoc.infraestructure.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationResponseDTO {
    private Long reservationId;
    private ClientDTO client;
    private List<TableDTO> tables;
    private int numberOfPeople;
    private LocalDate date;
    private LocalDateTime time;
}
