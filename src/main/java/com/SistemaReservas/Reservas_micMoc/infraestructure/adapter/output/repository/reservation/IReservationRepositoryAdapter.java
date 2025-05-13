package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.repository.reservation;

import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity.ReservationEntity;
import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IReservationRepositoryAdapter extends JpaRepository<ReservationEntity, Long> {

    @Query("""
    SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
    FROM ReservationEntity r
    JOIN r.tables t
    WHERE t IN :tables
    AND r.date = :date
    AND (
        (:start BETWEEN r.time AND r.endTime)
        OR (:end BETWEEN r.time AND r.endTime)
        OR (r.time BETWEEN :start AND :end)
    )
""")
    boolean existsOverlappingReservation(
            @Param("tables") List<TableEntity> tables,
            @Param("date") LocalDate date,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );




}
