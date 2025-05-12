package com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.repository.reservation;

import com.SistemaReservas.Reservas_micMoc.infraestructure.adapter.output.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IReservationRepositoryAdapter extends JpaRepository<ReservationEntity, Long> {

    @Query("""
    SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END
    FROM Reservation r
    JOIN r.tableInReservation t
    WHERE t.id IN :tableIds
      AND r.date = :requestedDate   -- Verificamos que la fecha coincida
      AND r.time < :endTime
      AND :startTime < r.time.plusMinutes(90)
""")
    boolean existsOverlappingReservation(@Param("tableIds") List<Long> tableIds,
                                         @Param("requestedDate") LocalDate requestedDate,
                                         @Param("startTime") LocalDateTime startTime,
                                         @Param("endTime") LocalDateTime endTime);


}
