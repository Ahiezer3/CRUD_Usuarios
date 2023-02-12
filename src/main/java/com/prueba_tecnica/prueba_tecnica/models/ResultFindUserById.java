package com.prueba_tecnica.prueba_tecnica.models;

public class ResultFindUserById extends ResultFindUser{

    private Long id;

    public ResultFindUserById(User user, boolean found, Long id) {
        super(user, found);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
