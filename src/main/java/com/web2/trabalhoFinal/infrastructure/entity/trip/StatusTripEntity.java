package com.web2.trabalhoFinal.infrastructure.entity.trip;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "status_trip")
public class StatusTripEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    @Column(name = "status_trip",nullable = false)
    private String status;
    
    StatusTripEntity(){}

    public StatusTripEntity(String status){
        this.status =  status;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

}
