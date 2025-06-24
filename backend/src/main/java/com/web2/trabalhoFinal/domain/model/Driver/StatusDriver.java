package com.web2.trabalhoFinal.domain.model.Driver;

public enum StatusDriver {
    
    DISPONIVEL("Disponível"),
    EM_VIAGEM("Em Viagem");

    // Atributo para armazenar o texto de exibição
    private final String displayName;

    // Construtor do enum
    StatusDriver(String displayName) {
        this.displayName = displayName;
    }

    // Getter para o texto de exibição
    public String getDisplayName() {
        return displayName;
    }
    public static StatusDriver fromString(String text) {
        
        for (StatusDriver s : StatusDriver.values()) {
            if (s.name().equalsIgnoreCase(text) || s.displayName.equalsIgnoreCase(text)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Nenhum status encontrado para o texto: " + text);
    }    
    
}

