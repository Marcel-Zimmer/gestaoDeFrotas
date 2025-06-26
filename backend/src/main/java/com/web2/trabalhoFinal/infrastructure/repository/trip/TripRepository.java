package com.web2.trabalhoFinal.infrastructure.repository.trip;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web2.trabalhoFinal.infrastructure.entity.trip.TripEntity;


@Repository
public interface TripRepository extends JpaRepository<TripEntity, Long> {

    @Query("SELECT v FROM TripEntity v " +
           "JOIN FETCH v.status " +
           "JOIN FETCH v.driver " +
           "JOIN FETCH v.vehicle " )
    List<TripEntity> findAllWithDetails();

    TripEntity findById(long id);
    
    @Query("SELECT t FROM TripEntity t " +
           "JOIN FETCH t.vehicle v " +
           "JOIN FETCH t.driver d " +
           "JOIN FETCH d.user u " +
           "JOIN FETCH t.destiny dest " +
           "JOIN FETCH t.status s " +
           "WHERE u.id = :userId " +
           "AND s.status IN ('AGENDADO', 'EM_VIAGEM') " + // <<< MUDANÇA AQUI
           "ORDER BY t.date ASC")
    List<TripEntity> findSchedulesByUserIdWithDetails(@Param("userId") Long userId); 
    
    @Query("SELECT t FROM TripEntity t " +
           "JOIN FETCH t.vehicle v " +
           "JOIN FETCH t.driver d " +
           "JOIN FETCH d.user u " +
           "JOIN FETCH t.destiny dest " +
           "JOIN FETCH t.status s " +
           "WHERE u.id = :userId AND s.status = 'FINALIZADO' " +
           "ORDER BY t.actualArrivalTime DESC") // <<< SUGESTÃO DE ORDENAÇÃO
    List<TripEntity> findSchedulesByUserIdEndTrip(@Param("userId") Long userId);    
}