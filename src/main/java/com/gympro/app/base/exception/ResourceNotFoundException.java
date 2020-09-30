package com.gympro.app.base.exception;

public class ResourceNotFoundException extends Exception {

  private static final long serialVersionUID = -4942845854761476097L;

  public ResourceNotFoundException(Exception e, String entityName) {
    super(e);
    setEntity(entityName);
  }

  public ResourceNotFoundException(String entityName) {
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
