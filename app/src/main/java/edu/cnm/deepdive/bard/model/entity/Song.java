package edu.cnm.deepdive.bard.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    indices = {
        @Index(value = {"song_name", "artist", "album"}, unique = true),
    },
    foreignKeys =
    @ForeignKey(
        entity = User.class,
        childColumns = "user_id",
        parentColumns = "user_id",
        onDelete = ForeignKey.CASCADE
    )
)
public class Song {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "song_id")
  private long songId;

  @ColumnInfo(name = "song_name")
  private String songName;

  private String artist;

  private String album;

  private int songDuration;

  @ColumnInfo(name = "user_id", index = true)
  private long userId;

  public long getSongId() {
    return songId;
  }

  public void setSongId(long songId) {
    this.songId = songId;
  }

  public String getSongName() {
    return songName;
  }

  public void setSongName(String songName) {
    this.songName = songName;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public String getAlbum() {
    return album;
  }

  public void setAlbum(String album) {
    this.album = album;
  }

  public int getSongDuration() {
    return songDuration;
  }

  public void setSongDuration(int songDuration) {
    this.songDuration = songDuration;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }
}
