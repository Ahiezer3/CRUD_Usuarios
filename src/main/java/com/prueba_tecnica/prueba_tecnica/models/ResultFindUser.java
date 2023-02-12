package com.prueba_tecnica.prueba_tecnica.models;

public class ResultFindUser {

    private User user;

    private boolean found;

    public ResultFindUser(User user, boolean found) {
        this.user = user;
        this.found = found;
    }

    public User getUser() {
        return user;
    }

    public boolean isFound() {
        return found;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFound(boolean found) {
        this.found = found;
    }
}
