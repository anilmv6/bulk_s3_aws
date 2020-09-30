package com.gympro.app.organization.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentDTO {

  public Long id;
  public String name;
  public String age;
  public String cat;
  public String lname;
  
  

  public StudentDTO(Long id, String name, String age, String cat, String lname) {
    super();
    this.id = id;
    this.name = name;
    this.age = age;
    this.cat = cat;
    this.lname = lname;
  }
}
