package com.web2.trabalhoFinal.infrastructure.entity.driver;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "driver")
public class DriverEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "isSuperUser")
    private boolean isSuperUser;

    @ManyToOne
    @JoinColumn(name = "id_phone_number", referencedColumnName = "id", nullable = false)
    private PhoneNumberEntity phoneNumber;

    @ManyToOne
    @JoinColumn(name = "id_cpf", referencedColumnName = "id", nullable = false)
    private CpfEntity cpf; 

    @ManyToOne
    @JoinColumn(name = "id_cnh", referencedColumnName = "id", nullable = false)
    private CnhEntity cnh; 

    @ManyToOne
    @JoinColumn(name = "id_address", referencedColumnName = "id", nullable = false)
    private AddressEntity address; 

    public DriverEntity() {}

    public DriverEntity(String name, String email, String password, boolean isSuperUser, PhoneNumberEntity phoneNumber,
            CpfEntity cpf, CnhEntity cnh, AddressEntity address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isSuperUser = isSuperUser;
        this.phoneNumber = phoneNumber;
        this.cpf = cpf;
        this.cnh = cnh;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isSuperUser() {
        return isSuperUser;
    }

    public PhoneNumberEntity getPhoneNumber() {
        return phoneNumber;
    }

    public CpfEntity getCpf() {
        return cpf;
    }

    public CnhEntity getCnh() {
        return cnh;
    }

    public AddressEntity getAddress() {
        return address;
    }
}