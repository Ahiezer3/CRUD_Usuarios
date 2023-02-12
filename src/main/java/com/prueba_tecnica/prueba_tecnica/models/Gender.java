package com.prueba_tecnica.prueba_tecnica.models;

public enum Gender {

    MALE("Hombre"),
    FEMALE("Mujer"),

    OTHER("Otro");

    private String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Gender findByDescription(String gender){

        for( Gender current : Gender.values() ){
            if( current.getDescription().toString().contains(gender) ){
                return current;
            }
        }

        return Gender.OTHER;
    }

    public static Gender findByName(String gender){

        for( Gender current : Gender.values() ){
            if( current.name().contains(gender) ){
                return current;
            }
        }

        return Gender.OTHER;
    }
}
