package edu.cnm.deepdive.bard.model.dto;

import com.google.gson.annotations.Expose;
import edu.cnm.deepdive.bard.model.entity.Song;
import java.util.List;

/**
 * Data Transfer Object for translating the items received from the web service in terms of Track
 * Information
 */
public class TracksResponse {

  @Expose
  private List<Item> items;

  /**
   * Returns the Items received from the Web Service
   */
  public List<Item> getItems() {
    return items;
  }

  /**
   * Sets the Items received from the Web Service
   */
  public void setItems(List<Item> items) {
    this.items = items;
  }

  /**
   * Nested Class for defining the Items received by the Web Service
   */
  public static class Item {

    @Expose
    private Song track;

    /**
     * Returns a Track Object from the Web Service
     */
    public Song getTrack() {
      return track;
    }

    /**
     * Sets a Track Object Received from the Web Service
     */
    public void setTrack(Song track) {
      this.track = track;
    }
  }

}
