package edu.cnm.deepdive.bard.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * Song entity stored in the database along with attributes, fields, foreign keys, and indices.
 */
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

  /**
   * Returns the id of a Song
   */
  public long getSongId() {
    return songId;
  }

  /**
   * Sets an id as the songId
   */
  public void setSongId(long songId) {
    this.songId = songId;
  }

  /**
   * Returns a Song by name
   */
  public String getSongName() {
    return songName;
  }

  /**
   * Sets a name as the songName
   */
  public void setSongName(String songName) {
    this.songName = songName;
  }

  /**
   * Returns the artist of a Song
   */
  public String getArtist() {
    return artist;
  }

  /**
   * Sets an artist as the Song's artist
   */
  public void setArtist(String artist) {
    this.artist = artist;
  }

  /**
   * Returns the album a song is found on
   */
  public String getAlbum() {
    return album;
  }

  /**
   * Sets an album as a Song's album
   */
  public void setAlbum(String album) {
    this.album = album;
  }

  /**
   * Returns the duration of a song
   */
  public int getSongDuration() {
    return songDuration;
  }

  /**
   * Sets a duration as the songDuration
   */
  public void setSongDuration(int songDuration) {
    this.songDuration = songDuration;
  }

  /**
   * Returns a User's id
   */
  public long getUserId() {
    return userId;
  }

  /**
   * Sets an id as a userId
   */
  public void setUserId(long userId) {
    this.userId = userId;
  }
}
