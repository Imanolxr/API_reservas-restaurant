package com.SistemaReservas.Reservas_micMoc.infraestructure.rest.controller;

import com.SistemaReservas.Reservas_micMoc.application.service.ReservationService;
import com.SistemaReservas.Reservas_micMoc.domain.model.Reservation;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.reservation.NewReservationDTOMapper;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.mapper.reservation.ReservationResponseDTOMapper;
import com.SistemaReservas.Reservas_micMoc.infraestructure.rest.dto.NewReservationDTO;
import com.SistemaReservas.Reservas_micMoc.infraestructure.rest.dto.ReservationResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class reservationController {

    private final ReservationService reservationService;
    private final NewReservationDTOMapper newReservationDTOMapper;
    private final ReservationResponseDTOMapper responseDTOMapper;


    public reservationController(ReservationService reservationService, NewReservationDTOMapper newReservationDTOMapper, ReservationResponseDTOMapper responseDTOMapper) {
        this.reservationService = reservationService;
        this.newReservationDTOMapper = newReservationDTOMapper;
        this.responseDTOMapper = responseDTOMapper;
    }

    @PostMapping("/new")
    public ResponseEntity<ReservationResponseDTO> newReservation(@RequestBody NewReservationDTO dto){
        Reservation newReservation = newReservationDTOMapper.toModel(dto);
        Reservation savedReservation = reservationService.createReservation(newReservation);
        ReservationResponseDTO response = responseDTOMapper.toDTO(savedReservation);

        return new ResponseEntity<ReservationResponseDTO>(response , HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{reservationId}")
    public ResponseEntity<String> deleteMapping(@PathVariable Long reservationId){
        reservationService.deleteReservation(reservationId);
        return new ResponseEntity<>("Reserva eliminada" , HttpStatus.OK);
    }

}
