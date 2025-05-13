package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    @NotNull(message = "id de cliente necesario")
    private ClientEntity client;

    @ManyToMany
    @NotBlank(message = "id de mesa necesario")
    @JoinTable(
            name = "reservation_table",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "table_id")
    )
    private List<TableEntity> tables;

    @Column(nullable = false)
    private int numberOfPeople;

    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private LocalDateTime endTime;





}
