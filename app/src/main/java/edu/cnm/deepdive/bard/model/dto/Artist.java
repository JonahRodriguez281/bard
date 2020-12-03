package edu.cnm.deepdive.bard.model.dto;

import com.google.gson.annotations.Expose;

public class Artist {

  @Expose
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
