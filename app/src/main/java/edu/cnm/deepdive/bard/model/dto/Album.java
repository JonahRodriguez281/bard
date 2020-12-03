package edu.cnm.deepdive.bard.model.dto;

import com.google.gson.annotations.Expose;

/**
 * Data Transfer Object that passes info from the API to the Application in regards to Album
 * Information
 */
public class Album {

  @Expose
  private String name;

  /**
   * Returns a name for the Album
   */
  public String getName() {
    return name;
  }

  /**
   * Sets a name for the Album
   */
  public void setName(String name) {
    this.name = name;
  }
}
