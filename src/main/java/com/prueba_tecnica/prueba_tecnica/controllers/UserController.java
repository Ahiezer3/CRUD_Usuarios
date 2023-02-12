package com.prueba_tecnica.prueba_tecnica.controllers;

import com.prueba_tecnica.prueba_tecnica.models.*;
import com.prueba_tecnica.prueba_tecnica.service.UserDao;
import com.prueba_tecnica.prueba_tecnica.service.UsuarioService;
import io.swagger.annotations.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import static java.lang.Long.parseLong;

/** This Controller defines endpoints for the API.
 * @author Abidan Ahiezer Carranza Talabera
 * @since 09/02/2023
 * @version 1.0
 */
@Api(value = "User Rest Controller", description = "REST API para usuario")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserDao userDao;

    @Autowired
    UsuarioService service;

    @ApiOperation(value = "Obtener todos los usuarios."
            ,notes = "Endpoint para la obtiención de todos aquellos usuarios registrados en la BDD.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtuvo correctamente."+
                    "\nCon los códigos de error internos: "+
                    "\n'code': 300, 'message': 'Usuarios econtrados.'"+
                    "\n'code': 301, 'message': 'No se encontraron usuarios.'"+
                    "\n'code': 304, 'message': 'Ocurrió un error al obtener los usuarios.'"),
            @ApiResponse(code = 400, message = "Bad Request.")})
    @RequestMapping(value = "/getAllUsers",method = RequestMethod.GET,produces = { "application/json" })
    @ResponseBody
    public ResponseEntity<Response> getAllEmployees(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){

        Response response = null;

        try {
            response = service.getAllUsers(pageNo, pageSize, sortBy);
        }catch(Exception e){
            return new ResponseEntity<Response>(null, new HttpHeaders(), HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "Obtener usuario."
            ,notes = "Endpoint para obtener un usuario específico.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtuvo correctamente."+
                    "\nCon los códigos de error internos: "+
                    "\n'code': 400, 'message': 'El usuario fue encontrado con éxito.'"+
                    "\n'code': 404, 'message': 'El usuario no existe.'"),
            @ApiResponse(code = 400, message = "Bad Request.")})

    @RequestMapping(value = "/getUser{id}",method = RequestMethod.GET,produces = { "application/json" })
    public ResponseEntity<Response> getUser(@RequestParam(value = "id", required = true) Long id){

        Response response = null;
        try {
            response = userDao.getUser(id);
        } catch(Exception e){
            return new ResponseEntity<Response>(null, new HttpHeaders(), HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);
    }
    @ApiOperation(value = "Borrar usuario."
            ,notes = "Endpoint para borrar un usuario específico.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtuvo correctamente."+
                    "\nCon los códigos de error internos: "+
                    "\n'code': 600, 'message': 'El usuario fue eliminado con éxito.'"+
                    "\n'code': 601, 'message': 'El usuario no fue eliminado con éxito.'"+
                    "\n'code': 404, 'message': 'El usuario no existe.'"),
            @ApiResponse(code = 400, message = "Bad Request.")})
    @RequestMapping(value = "/deleteUser{id}",method = RequestMethod.DELETE,produces = { "application/json" })
    public ResponseEntity<Response> deleteUser(@RequestParam(value = "id", required = true) Long id){

        Response response = null;
        try {
            response = userDao.deleteUser(id);
        }catch(Exception e){
            return new ResponseEntity<Response>(null, new HttpHeaders(), HttpStatus.BAD_GATEWAY);
        }

        return new ResponseEntity<Response>(response, new HttpHeaders(), HttpStatus.OK);
    }
    @ApiOperation(value = "Editar usuario."
            ,notes = "Endpoint para editar un usuario específico.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtuvo correctamente."+
                    "\nRedirección a url: /errorEdit.html o /successEdit.html según sea el caso."),
            @ApiResponse(code = 400, message = "Bad Request.")})
    @RequestMapping(value = "/editUser",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RedirectView editUser(
            @RequestParam(value = "idUser", required = true) String id,
            @RequestParam(value = "name", required = true) String name,
            @RequestParam("emails") String emails,
            @RequestParam(value = "gender", required = true) String gender,
            @RequestParam(value = "status", required = true) String status,
            @RequestParam(value = "file", required = false) MultipartFile file){

        Response response = createUser(id, name, emails, gender, status, file);

        if( response != null &&
                response.getSucced() == true &&
                response.getEntity() != null ) {
            userDao.editUser((User) response.getEntity());
        }else {
            return new RedirectView("/errorEdit.html");
        }
        return new RedirectView("/successEdit.html");

    }
    @ApiOperation(value = "Crear usuario."
            ,notes = "Endpoint para crear un usuario.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. El recurso se obtuvo correctamente."+
                    "\nRedirección a url: /errorRegister.html o /successRegister.html según sea el caso."),
            @ApiResponse(code = 400, message = "Bad Request.")})
    @RequestMapping(value = "/addUser{file}",method = RequestMethod.POST,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RedirectView addUser(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam("emails") String emails,
            @RequestParam(value = "gender", required = true) String gender,
            @RequestParam(value = "status", required = true) String status,
            @RequestParam(value = "file", required = false) MultipartFile file){

        Response response = createUser(null, name, emails, gender, status, file);

        if( response != null &&
                response.getSucced() == true &&
                response.getEntity() != null ) {
            userDao.addUser((User) response.getEntity());
        } else{
            return new RedirectView("/errorRegister.html");
        }

        return new RedirectView("/successRegister.html");

    }

    private Response createUser(String id,
                            String name,
                            String emails,
                            String gender,
                            String status,
                            MultipartFile file){

        Response response = new Response();
        response.setSucced(true);

        User user = new User();
        user.setName(name);
        user.setEmail(emails);
        user.setGender(Gender.findByName(gender));
        user.setStatus(Status.findByName(status));

        if( id != null && !id.equals("") ) {
            user.setId(parseLong(String.valueOf(id)));
        }

        if( file != null ) {
            String pathProyect = new File(".").getAbsolutePath();

            int index = file.getOriginalFilename().indexOf(".");
            String extension = file.getOriginalFilename().substring(index + 1);
            ;
            String path = pathProyect + "//src//main//resources//static//img//UploadImages";
            String namePicture = user.getEmail() + "_" + Calendar.getInstance().getTimeInMillis() + "." + extension;
            String absolutePath = Paths.get(path + "//" + namePicture).toString();

            try {
                Path directoryPath = Paths.get(path);
                if (!Files.exists(directoryPath)) {
                    File newDirectory = new File(path);

                    newDirectory.mkdir();
                }

                Files.write(Path.of(absolutePath), file.getBytes());
                user.setUrlImage(absolutePath);
                user.setNameImage(namePicture);

                FileInputStream myStream = new FileInputStream(user.getUrlImage());
                byte[] imageInBytes = IOUtils.toByteArray(myStream);
                String s = Base64.getEncoder().encodeToString(imageInBytes);
                user.setBase64Image(s);


            } catch (Exception e) {
                Response error = new Response();
                error.setSucced(false);
                error.setCode(ResponseCode.ERROR_UPLOAD.getCode());
                error.setMessage(ResponseCode.ERROR_UPLOAD.getDescription());

                return response;
            }
        }

        response.setEntity(user);

        return response;

    }

}
