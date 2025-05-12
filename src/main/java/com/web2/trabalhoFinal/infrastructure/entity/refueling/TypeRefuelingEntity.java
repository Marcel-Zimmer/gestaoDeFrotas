package com.web2.trabalhoFinal.infrastructure.entity.refueling;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "type_refueling")
public class TypeRefuelingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="type_refueling", nullable = false)
    private String typeRefueling;

    public TypeRefuelingEntity(){}
    public TypeRefuelingEntity(String typeRefueling) {
        this.typeRefueling = typeRefueling;
    }

    public Long getId() {
        return id;
    }

    public String getTypeRefueling() {
        return typeRefueling;
    }

}
