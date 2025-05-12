package com.web2.trabalhoFinal.domain.model.vehicle;

import java.time.Year;

public class YearVehicle {

    private String year; 

    public YearVehicle(String year) {
        this.validate(year);
        this.year = year;
    }

    private void validate(String year) {
        try {
            Year yearValidate = Year.parse(year);
            if(yearValidate.getValue() < Year.of(1960).getValue()){
                throw new IllegalArgumentException("Ano Invalido");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Ano Invalido");
        }
        
    }

    public String getYear() {
        return year;
    }

    
}
