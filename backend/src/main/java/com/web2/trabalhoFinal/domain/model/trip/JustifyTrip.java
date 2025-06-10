package com.web2.trabalhoFinal.domain.model.trip;

public class JustifyTrip {

    private String justify;

    public JustifyTrip(String justify) {
        this.validate(justify);
        this.justify = justify;
    }


    private void validate(String justify) {
        if (justify == null || justify.isEmpty()) {
            throw new IllegalArgumentException("Justificativa não pode ser vazia.");
        }
        if (justify.length() > 255) {
            throw new IllegalArgumentException("Justificativa não pode ter mais de 255 caracteres.");
        }
        if (!justify.matches("^[A-Za-z0-9\\s]*$")) {
            throw new IllegalArgumentException("Justificativa só pode conter letras, números e espaços.");
        }
    }
    
    public String getJustify() {
        return justify;
    }

}
