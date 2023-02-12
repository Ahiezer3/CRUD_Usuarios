package com.prueba_tecnica.prueba_tecnica.service;

import com.prueba_tecnica.prueba_tecnica.models.Response;
import com.prueba_tecnica.prueba_tecnica.models.ResponseCode;
import com.prueba_tecnica.prueba_tecnica.models.User;
import com.prueba_tecnica.prueba_tecnica.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UsuarioService {
    @Autowired
    UserRepository repository;

    public Response getAllUsers(Integer pageNo, Integer pageSize, String sortBy)
    {
        boolean suceedResponse = false;
        int codeResponse = ResponseCode.NO_USERS_FOUND.getCode();;
        String messageResponse = ResponseCode.NO_USERS_FOUND.getDescription();

        Response response = null;

        List<User> usersFound = null;

        try {

            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

            Page<User> pagedResult = repository.findAll(paging);


            if (pagedResult.hasContent()) {
                usersFound = (List<User>) pagedResult.getContent();
            } else {
                usersFound = new ArrayList<User>();
            }

            if( usersFound != null && !usersFound.isEmpty() ){
                suceedResponse = true;
                codeResponse = ResponseCode.USERS_FOUND.getCode();;
                messageResponse = ResponseCode.USERS_FOUND.getDescription();
            }

            response = new Response(suceedResponse,codeResponse,messageResponse,null,usersFound);
            response.setPageNo(pageNo);
            response.setPageSize(pageSize);
            response.setTotalSizeInThisPage(usersFound.size());
            response.setTotalPages(pagedResult.getTotalPages());
            response.setTotalElements(Integer.parseInt(String.valueOf(pagedResult.getTotalElements())));

        } catch(Exception e){
            response = new Response(suceedResponse,ResponseCode.USERS_FOUND_ERROR.getCode(), ResponseCode.USERS_FOUND_ERROR+" "+e.getMessage(),null,null);
            response.setPageNo(pageNo);
            response.setPageSize(pageSize);

        } finally {
            return response;
        }
    }
}
