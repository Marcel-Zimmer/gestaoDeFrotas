package com.web2.trabalhoFinal.infrastructure.entity.driver;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    
    @Column(name = "zipCode")
    private  String zipCode;
    @Column(name = "street")
    private  String street;
    @Column(name = "complement")
    private  String complement;
    @Column(name = "unit")
    private  String unit;
    @Column(name = "neighborhood")
    private  String neighborhood;
    @Column(name = "city")
    private  String city;
    @Column(name = "stateAbbreviation")
    private  String stateAbbreviation;
    @Column(name = "state")
    private  String state;
    @Column(name = "region")
    private  String region;
    @Column(name = "ibgeCode")
    private  String ibgeCode;
    @Column(name = "giaCode")
    private  String giaCode;
    @Column(name = "ddd")
    private  String ddd;
    @Column(name = "siafiCode")
    private  String siafiCode;
    @Column(name = "numberAddress")
    private  String numberAddress;

    public AddressEntity(){

    }

    public AddressEntity(String zipCode, String street, String complement, String unit, String neighborhood,
            String city, String stateAbbreviation, String state, String region, String ibgeCode, String giaCode,
            String ddd, String siafiCode, String numberAddress) {
        this.zipCode = zipCode;
        this.street = street;
        this.complement = complement;
        this.unit = unit;
        this.neighborhood = neighborhood;
        this.city = city;
        this.stateAbbreviation = stateAbbreviation;
        this.state = state;
        this.region = region;
        this.ibgeCode = ibgeCode;
        this.giaCode = giaCode;
        this.ddd = ddd;
        this.siafiCode = siafiCode;
        this.numberAddress = numberAddress;
    }

    public Long getId() {
        return id;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getStreet() {
        return street;
    }

    public String getComplement() {
        return complement;
    }

    public String getUnit() {
        return unit;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public String getState() {
        return state;
    }

    public String getRegion() {
        return region;
    }

    public String getIbgeCode() {
        return ibgeCode;
    }

    public String getGiaCode() {
        return giaCode;
    }

    public String getDdd() {
        return ddd;
    }

    public String getSiafiCode() {
        return siafiCode;
    }

    public String getNumberAddress() {
        return numberAddress;
    }

    
}
