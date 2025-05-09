package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "número de mesa necesario")
    private String tableNumber;

    @NotNull(message = "capacidad de mesa necesaria")
    private int capacity;

    @ManyToMany(mappedBy = "tables")
    private List<ReservationEntity> reservations;




}
