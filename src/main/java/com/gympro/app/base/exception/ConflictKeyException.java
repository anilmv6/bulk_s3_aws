/**
 * 
 */
package com.gympro.app.base.exception;

import org.springframework.validation.ObjectError;


public class ConflictKeyException extends Exception {

  private static final long serialVersionUID = -15419048752687839L;

  private ObjectError objectError;

  /**
   * @return the objeError
   */
  public ObjectError getObjectError() {
    return objectError;
  }

  /**
   * @param objeError the objeError to set
   */
  public void setObjectError(ObjectError objeError) {
    this.objectError = objeError;
  }

}
