package com.gympro.app.base.exception;//package com.gympro.app.organization.exception;
//
//import com.lowes.service.customerservice.common.enums.EnumErrorCode;
//import org.springframework.validation.ObjectError;
//
//public class ForbiddenException extends Exception {
//
//  private static final long serialVersionUID = -15419048752687842L;
//  private ObjectError error;
//
//  /* defaults to insufficient user permissions error */
//  public ForbiddenException() {
//    this.error = new ObjectError(ForbiddenException.class.getSimpleName(),
//        EnumErrorCode.INSUFFICIENT_USER_PERMISSIONS.toString());
//  }
//
//  public void setObjectError(ObjectError allErrors) {
//    this.error = allErrors;
//  }
//
//  public ObjectError getObjectError() {
//    return this.error;
//  }
//}
