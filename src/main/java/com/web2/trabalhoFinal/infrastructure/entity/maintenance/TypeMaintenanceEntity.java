package com.web2.trabalhoFinal.infrastructure.entity.maintenance;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "type_maintenance")
public class TypeMaintenanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="type_maintenance", nullable = false)
    private String typeMaintenance;

    public TypeMaintenanceEntity(){}

    public TypeMaintenanceEntity(String typeMaintenance) {
        this.typeMaintenance = typeMaintenance;
    }

    public Long getId() {
        return id;
    }

    public String getTypeMaintenance() {
        return typeMaintenance;
    }
    
}
