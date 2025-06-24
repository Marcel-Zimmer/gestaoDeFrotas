package com.web2.trabalhoFinal.domain.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
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

    public Address(){
        
    }

    public Address(
        String zipCode,
        String street,
        String complement,
        String unit,
        String neighborhood,
        String city,
        String stateAbbreviation,
        String state,
        String region,
        String ibgeCode,
        String giaCode,
        String ddd,
        String siafiCode,
        String numberAddress
    ) {
        this.zipCode = sanitize(zipCode);
        this.street = sanitize(street);
        this.complement = sanitize(complement);
        this.unit = sanitize(unit);
        this.neighborhood = sanitize(neighborhood);
        this.city = sanitize(city);
        this.stateAbbreviation = sanitize(stateAbbreviation);
        this.state = sanitize(state);
        this.region = sanitize(region);
        this.ibgeCode = sanitize(ibgeCode);
        this.giaCode = sanitize(giaCode);
        this.ddd = sanitize(ddd);
        this.siafiCode = sanitize(siafiCode);
        this.numberAddress = sanitize(numberAddress);
    }

    public String sanitize(String input) {
        if (input == null) return null;
        return input.replaceAll("[^\\p{L}\\p{N}\\s\\-]", "");
    }

    public String getZipCode() { return zipCode; }
    public String getStreet() { return street; }
    public String getComplement() { return complement; }
    public String getUnit() { return unit; }
    public String getNeighborhood() { return neighborhood; }
    public String getCity() { return city; }
    public String getStateAbbreviation() { return stateAbbreviation; }
    public String getState() { return state; }
    public String getRegion() { return region; }
    public String getIbgeCode() { return ibgeCode; }
    public String getGiaCode() { return giaCode; }
    public String getDdd() { return ddd; }
    public String getSiafiCode() { return siafiCode; }
    public String getNumberAddress() { return numberAddress; }
}   

 