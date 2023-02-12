package com.prueba_tecnica.prueba_tecnica.models;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

public class Response {
    @ApiModelProperty(position = 0, value = "Satisfactorio.")
    private Boolean succed;

    @ApiModelProperty(position = 1, value = "Código interno.")
    private int code;

    @ApiModelProperty(position = 2, value = "Mensaje interno.")
    private String message;

    @ApiModelProperty(position = 3, value = "Entidad User encontrada.")
    private MyEntity entity;
    @ApiModelProperty(position = 4, value = "Entidades User encontradas.")
    private List<User> entities;
    @ApiModelProperty(position = 5, value = "Total de entidades por página.")
    private int pageSize;
    @ApiModelProperty(position = 6, value = "Número actual de página.")
    private int pageNo;
    @ApiModelProperty(position = 7, value = "Total de entidades en la página actual.")
    private int totalSizeInThisPage;
    @ApiModelProperty(position = 9, value = "Total de entidades encontradas.")
    private int totalElements;
    @ApiModelProperty(position = 9, value = "Total de páginas.")
    private int totalPages;

    public Response() {

    }

    public Response(Boolean succed, int code, String message, MyEntity entity, List<User> entities) {
        this.succed = succed;
        this.code = code;
        this.message = message;
        this.entity = entity;
        this.entities = entities;
    }

    public Boolean getSucced() {
        return succed;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public MyEntity getEntity() {
        return entity;
    }

    public void setSucced(Boolean succed) {
        this.succed = succed;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setEntity(MyEntity entity) {
        this.entity = entity;
    }

    public List<User> getEntities() {
        if( entities == null ){
            entities = new ArrayList<>();
        }
        return entities;
    }

    public void setEntities(List<User> entities) {
        this.entities = entities;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalSizeInThisPage() {
        return totalSizeInThisPage;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalSizeInThisPage(int totalSizeInThisPage) {
        this.totalSizeInThisPage = totalSizeInThisPage;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
