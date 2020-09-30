package com.gympro.app.organization.dto;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class JsonDataDTO {

  @SerializedName("Sheet1")
  private List<StudentDTO> studentList;

  public List<StudentDTO> getStudentList() {
    return studentList;
  }

  public void setStudentList(List<StudentDTO> studentList) {
    this.studentList = studentList;
  }
  
  
}
