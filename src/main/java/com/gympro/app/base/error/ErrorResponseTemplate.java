package com.gympro.app.base.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.OffsetDateTimeSerializer;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

@Component
public class ErrorResponseTemplate {

  public static final String RESOURCE_JSON_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ssxxx";

  @JsonSerialize(using = OffsetDateTimeSerializer.class)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = RESOURCE_JSON_DATE_PATTERN)
  private OffsetDateTime timeStamp;

  private List<ErrorResponse> errors;

  /**
   *
   * @return
   */
  public OffsetDateTime getTimeStamp() {
    return timeStamp;
  }

  /**
   *
   * @param timeStamp
   */
  public void setTimeStamp(OffsetDateTime timeStamp) {
    this.timeStamp = timeStamp;
  }

  /**
   * @return the errors
   */
  public List<ErrorResponse> getErrors() {
    return errors;
  }

  /**
   * @param errors the errors to set
   */
  public void setErrors(List<ErrorResponse> errors) {
    this.errors = errors;
  }
}
