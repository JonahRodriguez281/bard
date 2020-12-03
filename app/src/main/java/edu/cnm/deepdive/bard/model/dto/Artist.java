package edu.cnm.deepdive.bard.model.dto;

import com.google.gson.annotations.Expose;

/**
 * Data Transfer Object that passes info from the API to the Application in regards to Artist
 * Information
 */
public class Artist {

  @Expose
  private String name;

  /**
   * Returns the name of the Artist
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name for the Artist
   */
  public void setName(String name) {
    this.name = name;
  }
}
