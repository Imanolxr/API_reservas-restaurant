package com.SistemaReservas.Reservas_micMoc.infraestructure.rest.controller;

import com.SistemaReservas.Reservas_micMoc.application.service.ReservationService;
import com.SistemaReservas.Reservas_micMoc.domain.model.Reservation;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.reservation.ReservationMapper;
import com.SistemaReservas.Reservas_micMoc.infraestructure.rest.dto.NewReservationDTO;
import com.SistemaReservas.Reservas_micMoc.infraestructure.rest.dto.ReservationResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class reservationController {

    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;


    public reservationController(ReservationService reservationService, ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @PostMapping("/new")
    public ResponseEntity<ReservationResponseDTO> newReservation(@RequestBody NewReservationDTO dto){
        reservationService.createReservation(reservationMapper.toModel(dto));
        return null;
    }
}
