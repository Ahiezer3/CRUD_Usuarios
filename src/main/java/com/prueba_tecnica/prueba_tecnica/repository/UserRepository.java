package com.prueba_tecnica.prueba_tecnica.repository;

import com.prueba_tecnica.prueba_tecnica.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {


}
