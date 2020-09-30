package com.gympro.app.base.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -4942845854761476097L;

    public BusinessException(Exception e, String entityName) {
        super(e);
        setEntity(entityName);
    }

    public BusinessException(String entityName) {
        setEntity(entityName);
    }

    private String entity;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }
}
