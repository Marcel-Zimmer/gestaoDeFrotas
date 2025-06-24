package com.web2.trabalhoFinal.infrastructure.repository.trip;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
}