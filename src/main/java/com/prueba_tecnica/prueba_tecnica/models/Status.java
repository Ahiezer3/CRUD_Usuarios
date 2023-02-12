package com.prueba_tecnica.prueba_tecnica.models;

public enum Status {

    ACTIVE("Activo"),
    INACTIVE("Inactivo"),

    OTHER("Otro");

    private String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Status findByDescription(String status){

        for( Status current : Status.values() ){
            if( current.getDescription().toString().contains(status) ){
                return current;
            }
        }

        return Status.OTHER;
    }

    public static Status findByName(String status){

        for( Status current : Status.values() ){
            if( current.name().contains(status) ){
                return current;
            }
        }

        return Status.OTHER;
    }
}
