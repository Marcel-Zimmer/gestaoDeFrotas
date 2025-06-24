package com.web2.trabalhoFinal.infrastructure.entity.driver;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cnh")
public class CnhEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cnh", nullable = false, unique = true, length = 11)
    private String cnh;

    @Column(name = "dateExpiration", nullable = false)
    private LocalDate dateExpiration;

    public CnhEntity(){

    }

    public CnhEntity(String cnh, LocalDate dateExpiration){
        this.cnh = cnh;
        this.dateExpiration = dateExpiration;
    }

    public Long getId() {
        return id;
    }

    public String getCnh() {
        return cnh;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
 
}
