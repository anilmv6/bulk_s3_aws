package com.gympro.app.organization.enums;

public enum EnumCustomerType {
  LEAD("lead"), CUSTOMER("customer");

  private String description;

  EnumCustomerType(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }
}
