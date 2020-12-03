package edu.cnm.deepdive.bard.model.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import edu.cnm.deepdive.bard.model.dto.Album;
import edu.cnm.deepdive.bard.model.dto.Artist;
import java.util.List;

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

  @ColumnInfo(name = "spotify_key")
  @Expose
  @SerializedName("id")
  private String spotifyKey;

  @ColumnInfo(name = "song_name")
  @Expose
  @SerializedName("name")
  private String songName;

  private String artist;

  @Expose
  @Ignore
  private List<Artist> artists;

  private String album;

  @Expose
  @SerializedName("album")
  @Ignore
  private Album albumDto;

  @ColumnInfo(name = "song_duration")
  @Expose
  @SerializedName("duration_ms")
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

  public String getSpotifyKey() {
    return spotifyKey;
  }

  public void setSpotifyKey(String spotifyKey) {
    this.spotifyKey = spotifyKey;
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

  public List<Artist> getArtists() {
    return artists;
  }

  public void setArtists(List<Artist> artists) {
    this.artists = artists;
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

  public Album getAlbumDto() {
    return albumDto;
  }

  public void setAlbumDto(Album albumDto) {
    this.albumDto = albumDto;
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
