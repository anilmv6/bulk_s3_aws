package com.gympro.app.base.exception;

import org.springframework.validation.ObjectError;

import java.util.List;


public class BadRequestException extends Exception {

  private static final long serialVersionUID = -15419048752687839L;

  private List<ObjectError> allErrors;

  public void setObjectErrors(List<ObjectError> allErrors) {
    this.allErrors = allErrors;
  }

  public List<ObjectError> getObjectErrors() {
    return this.allErrors;
  }
}
