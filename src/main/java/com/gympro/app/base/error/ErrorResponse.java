package com.gympro.app.base.error;

public class ErrorResponse {

  private String code;

  private String message;

  public ErrorResponse() {}

  public ErrorResponse(final String code, final String message) {
    this.code = code;
    this.message = message;
  }

  /**
   * @return the messageContent
   */
  public String getMessage() {
    return message;
  }

  /**
   * @param message the messageContent to set
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * @param code the code to set
   */
  public void setCode(String code) {
    this.code = code;
  }

}
