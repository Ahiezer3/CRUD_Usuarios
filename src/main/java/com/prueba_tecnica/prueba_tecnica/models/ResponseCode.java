package com.prueba_tecnica.prueba_tecnica.models;

public enum ResponseCode {

    addUser_OK(100,"El usuario fue registrado con éxito."),
    addUser_ERROR(101,"El usuario no fue registrado con éxito."),

    editUser_OK(200,"El usuario fue editado con éxito."),
    editUser_ERROR(201,"El usuario no fue editado con éxito."),

    deleteUser_OK(600,"El usuario fue eliminado con éxito."),
    deleteUser_ERROR(601,"El usuario no fue eliminado con éxito."),

    USERS_FOUND(300,"Usuarios encontrados."),

    NO_USERS_FOUND(301,"No se encontraron usuarios."),

    USERS_FOUND_ERROR(304,"Ocurrió un error al obtener los usuarios."),

    USER_EXISTS(400,"El usuario fue encontrado con éxito."),
    ERROR_USER_NOT_EXISTS(404,"El usuario no existe."),

    ERROR_UPLOAD(450,"Error al cargar el archivo.")

    ;

    private int code;
    private String description;

    ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
