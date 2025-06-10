package com.web2.trabalhoFinal.infrastructure.entity.driver;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ddd_number")
public class DddNumberEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ddd_number",nullable = false)
    private String dddNumber;

    public DddNumberEntity() {
    }
    public DddNumberEntity(String dddNumber) {
        this.dddNumber = dddNumber;
    }

    public Long getId() {
        return id;
    }

    public String getDddNumber() {
        return dddNumber;
    }


}
