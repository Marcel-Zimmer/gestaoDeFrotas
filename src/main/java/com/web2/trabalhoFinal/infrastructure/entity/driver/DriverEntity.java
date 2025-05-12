package com.web2.trabalhoFinal.infrastructure.entity.driver;


import com.web2.trabalhoFinal.infrastructure.entity.user.UserEntity;
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

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_ddd_number", referencedColumnName = "id", nullable = false)
    private DddNumberEntity dddNumber;

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

    public DriverEntity(Long id) {
        this.id = id;
    }

    public DriverEntity(UserEntity user ,DddNumberEntity dddNumber, PhoneNumberEntity phoneNumber,
            CpfEntity cpf, CnhEntity cnh, AddressEntity address) {
        this.user = user;
        this.dddNumber = dddNumber;
        this.phoneNumber = phoneNumber;
        this.cpf = cpf;
        this.cnh = cnh;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public DddNumberEntity getDddNumber() {
        return dddNumber;
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