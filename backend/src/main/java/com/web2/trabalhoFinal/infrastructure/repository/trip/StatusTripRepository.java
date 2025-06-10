package com.web2.trabalhoFinal.infrastructure.repository.trip;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.web2.trabalhoFinal.infrastructure.entity.trip.StatusTripEntity;


@Repository
public interface StatusTripRepository extends JpaRepository<StatusTripEntity, Long> {
    Optional<StatusTripEntity> findByStatus(String status);
}