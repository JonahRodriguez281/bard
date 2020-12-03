package edu.cnm.deepdive.bard.model.dto;

import com.google.gson.annotations.Expose;
import edu.cnm.deepdive.bard.model.entity.Song;
import java.util.List;

public class TracksResponse {

  @Expose
  private List<Item> items;

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public static class Item {

    @Expose
    private Song track;

    public Song getTrack() {
      return track;
    }

    public void setTrack(Song track) {
      this.track = track;
    }
  }

}
