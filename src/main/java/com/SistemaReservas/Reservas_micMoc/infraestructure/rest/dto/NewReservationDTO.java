package com.SistemaReservas.Reservas_micMoc.infraestructure.rest.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class NewReservationDTO {

    private Long clientId; // si ya existe

    @Valid
    private ClientDTO client; // si es nuevo

    @NotEmpty
    private List<Long> tableIds;

    @Min(1)
    private int numberOfPeople;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalDateTime time;
}
