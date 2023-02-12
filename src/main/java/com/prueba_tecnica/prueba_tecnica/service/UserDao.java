package com.prueba_tecnica.prueba_tecnica.service;

import com.prueba_tecnica.prueba_tecnica.models.Response;
import com.prueba_tecnica.prueba_tecnica.models.User;

public interface UserDao {

    Response getUsers();

    Response getUser(Long id);

    Response deleteUser(Long id);

    Response editUser(User user);

    Response addUser(User user);
}
