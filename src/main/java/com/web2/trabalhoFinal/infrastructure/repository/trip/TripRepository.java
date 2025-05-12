package com.web2.trabalhoFinal.infrastructure.repository.trip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.web2.trabalhoFinal.infrastructure.entity.trip.TripEntity;


@Repository
public interface TripRepository extends JpaRepository<TripEntity, Long> {

}