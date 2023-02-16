package com.prueba_tecnica.prueba_tecnica.models;

public enum Enviroment {

    DEVELOP("/"),
    DEPLOY("/prueba_tecnica/")
    ;

    private String description;

    Enviroment(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
