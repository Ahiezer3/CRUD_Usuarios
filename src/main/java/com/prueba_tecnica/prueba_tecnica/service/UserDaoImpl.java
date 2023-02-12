package com.prueba_tecnica.prueba_tecnica.service;

import com.prueba_tecnica.prueba_tecnica.models.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/** This class works with entities for persist them.
 * @author Abidan Ahiezer Carranza Talabera
 * @since 09/02/2023
 * @version 1.0
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public Response getUsers() {

        boolean suceedResponse = false;
        int codeResponse = ResponseCode.NO_USERS_FOUND.getCode();;
        String messageResponse = ResponseCode.NO_USERS_FOUND.getDescription();

        Response response = null;
        try{

            String query = "FROM User";
            List<User> usersFound = entityManager.createQuery(query).getResultList();

            if( usersFound != null && !usersFound.isEmpty() ){
                codeResponse = ResponseCode.USERS_FOUND.getCode();;
                messageResponse = ResponseCode.USERS_FOUND.getDescription();
            }

            response = new Response(suceedResponse,codeResponse,messageResponse,null,usersFound);
        } catch(Exception e){
            response = new Response(suceedResponse,ResponseCode.USERS_FOUND_ERROR.getCode(), ResponseCode.USERS_FOUND_ERROR+" "+e.getMessage(),null,null);
        } finally {
            return response;
        }

    }

    @Override
    public Response getUser(Long id) {

        boolean suceedResponse = false;
        int codeResponse = ResponseCode.ERROR_USER_NOT_EXISTS.getCode();;
        String messageResponse = ResponseCode.ERROR_USER_NOT_EXISTS.getDescription();

        Response response = null;
        try{
            ResultFindUserById result = findUserById(id);

            if ( result != null && result.isFound() ) {
                suceedResponse = true;
                codeResponse = ResponseCode.USER_EXISTS.getCode();
                messageResponse = ResponseCode.USER_EXISTS.getDescription();

            }

            response = new Response(suceedResponse,codeResponse,messageResponse,result.getUser(),null);
        } catch(Exception e){
            response = new Response(suceedResponse,codeResponse,messageResponse+" "+e.getMessage(),null,null);
        } finally {
            return response;
        }
    }

    @Override
    public Response deleteUser(Long id) {

        boolean suceedResponse = false;
        int codeResponse = ResponseCode.deleteUser_ERROR.getCode();
        String messageResponse = ResponseCode.deleteUser_ERROR.getDescription();

        Response response = null;
        try{
            ResultFindUserById result = findUserById(id);

            if ( result != null && result.isFound() ) {
                entityManager.remove(result.getUser());
                suceedResponse = true;
                codeResponse = ResponseCode.deleteUser_OK.getCode();
                messageResponse = ResponseCode.deleteUser_OK.getDescription();

            } else{
                codeResponse = ResponseCode.ERROR_USER_NOT_EXISTS.getCode();
                messageResponse = ResponseCode.ERROR_USER_NOT_EXISTS.getDescription();
            }

            response = new Response(suceedResponse,codeResponse,messageResponse,result.getUser(),null);
        } catch(Exception e){
            response = new Response(suceedResponse,codeResponse,messageResponse+" "+e.getMessage(),null,null);
        } finally {
            return response;
        }

    }

    @Override
    public Response editUser(User user) {

        boolean suceedResponse = false;
        int codeResponse = ResponseCode.editUser_ERROR.getCode();
        String messageResponse = ResponseCode.editUser_ERROR.getDescription();

        Response response = null;

        try{
           ResultFindUserById result = findUserById(user.getId());

            if( result != null && result.isFound() ) {
                user.setDateRegister(result.getUser().getDateRegister());
                user.setDateLastEdited(new Date());

                if( result.getUser().getBase64Image() != null && (user.getBase64Image() == null || user.getBase64Image().equals("")) ) {
                    user.setBase64Image(result.getUser().getBase64Image());
                }
                entityManager.merge(user);

                suceedResponse = true;
                codeResponse = ResponseCode.editUser_OK.getCode();
                messageResponse = ResponseCode.editUser_OK.getDescription();

            } else{
                codeResponse = ResponseCode.ERROR_USER_NOT_EXISTS.getCode();
                messageResponse = ResponseCode.ERROR_USER_NOT_EXISTS.getDescription();
            }

            response = new Response(suceedResponse,codeResponse,messageResponse,user,null);
        } catch(Exception e) {
            response = new Response(suceedResponse,codeResponse,messageResponse+" "+e.getMessage(),user,null);
        } finally{
            return response;
        }


    }

    @Override
    public Response addUser(User user) {

        Response response = null;

        user.setDateRegister(new Date());

        try {
            entityManager.merge(user);

            response = new Response(true, ResponseCode.addUser_OK.getCode(), ResponseCode.addUser_OK.getDescription(), user,null);
        } catch(Exception e){
            response = new Response(false,ResponseCode.addUser_ERROR.getCode(), ResponseCode.addUser_ERROR.getDescription()+" "+e.getMessage(),user,null);
        } finally {
            return response;
        }

    }

    private ResultFindUserById findUserById(Long id) throws IOException {

        ResultFindUserById result = new ResultFindUserById(null,false,id);

        User userFound = entityManager.find(User.class,id);
       if( userFound != null ) {
           result.setFound(true);
           result.setUser(userFound);
       }

       return result;

    }

    private ResultFindUserByEmail findUserByEmail(String email){

        ResultFindUserByEmail result = new ResultFindUserByEmail(null,false,email);

        User userFound = entityManager.find(User.class,email);

        if( userFound != null ){
            result.setFound(true);
            result.setUser(userFound);
        }

        return result;
    }

}
