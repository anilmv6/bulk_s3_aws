package com.gympro.app.base.exception.handler;


import com.gympro.app.base.error.ErrorResponse;
import com.gympro.app.base.error.ErrorResponseTemplate;
import com.gympro.app.base.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler {

  private static final int ZERO = 0;

  private static final String COLON = ":";
  private static final String ERROR_ONE = "` field is invalid. Only ";
  private static final String ERROR_TWO = " values allowed.";

  /**
   * Handles Exceptions related to Bad Requests
   * 
   * @param ex
   * @return
   */
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponseTemplate> handleBadRequestException(BadRequestException ex) {
    ErrorResponseTemplate errorResponseTemplate = new ErrorResponseTemplate();
    errorResponseTemplate.setTimeStamp(OffsetDateTime.now());
    List<ErrorResponse> errorResponseList = new ArrayList<>();
    for (final ObjectError error : ex.getObjectErrors()) {
//      errorResponseList.add(new ErrorResponse(error.getDefaultMessage(),
//          EnumErrorCode.valueOf(error.getDefaultMessage()).getErrorDescription()));
    }
    errorResponseTemplate.setErrors(errorResponseList);
    return new ResponseEntity<>(errorResponseTemplate, HttpStatus.BAD_REQUEST);
  }


}
