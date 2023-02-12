package com.prueba_tecnica.prueba_tecnica.models;

public class ResultFindUserByEmail extends ResultFindUser{

    private String email;

    public ResultFindUserByEmail(User user, boolean found, String email) {
        super(user, found);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
