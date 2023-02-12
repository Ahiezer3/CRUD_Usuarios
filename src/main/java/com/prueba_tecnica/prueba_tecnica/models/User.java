package com.prueba_tecnica.prueba_tecnica.models;

/** This class is an entity for represent User object, for persist and get those.
 * @author Abidan Ahiezer Carranza Talabera
 * @since 09/02/2023
 * @version 1.0
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="users")
public class User extends MyEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="iduser")
    @ApiModelProperty(position = 0, value = "Identificador.")
    private Long id;
    @Column(name="name")
    @ApiModelProperty(position = 1, value="Nombre.")
    private String name;
    @Column(name="email")
    @ApiModelProperty(position = 2, value="Emails separados por comas.")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name="gender")
    @ApiModelProperty(position = 3, value = "Género.")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    @ApiModelProperty(position = 4, value = "Estatus.")
    private Status status;

    @Column(name="date_registered")
    @ApiModelProperty(position = 5, value = "Fecha de registro.")
    private Date dateRegister;

    @Column(name="date_last_edited")
    @ApiModelProperty(position = 6, value = "Fecha de edición.")
    private Date dateLastEdited;

    @Column(name="url_image")
    @ApiModelProperty(position = 7)
    @JsonIgnore
    private String urlImage;

    @Column(name="name_image")
    @JsonIgnore
    @ApiModelProperty(position = 8)
    private String nameImage;

    @Column(name="base64Image")
    @ApiModelProperty(position = 9, value = "Imagen en base64")
    private String base64Image;

    public User(){

    }
    public User(Long id, String name, String email, Gender gender, Status status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public Date getDateLastEdited() {
        return dateLastEdited;
    }

    public void setDateLastEdited(Date dateLastEdited) {
        this.dateLastEdited = dateLastEdited;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    public String getBase64Image() {
        return base64Image;
    }
}
