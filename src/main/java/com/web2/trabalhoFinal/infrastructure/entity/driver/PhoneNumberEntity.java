package com.web2.trabalhoFinal.infrastructure.entity.driver;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "phone_number")
public class PhoneNumberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phoneNumber", nullable = false, length = 9)
    private String phoneNumber;

    @Column(name = "ddd",nullable = false)
    private String dddNumber;

    public PhoneNumberEntity(){

    }

    public PhoneNumberEntity(String phoneNumber, String dddNumber){
        this.phoneNumber = phoneNumber;
        this.dddNumber = dddNumber;
    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDddNumber() {
        return dddNumber;
    }


}
