package edu.cnm.deepdive.bard.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(
    indices = @Index(value = "spotify_playlist_key"),
    foreignKeys = {
        @ForeignKey(
            entity = User.class,
            childColumns = "user_id",
            parentColumns = "user_id",
            onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
            entity = TaskType.class,
            childColumns = "task_type_id",
            parentColumns = "task_type_id",
            onDelete = ForeignKey.CASCADE
        )
    }
)
public class Task {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "task_id")
  private long taskId;

  @ColumnInfo(name = "task_name")
  private String taskName;

  private String description;

  private int duration;

  private Date start;

  private Date end;

  @ColumnInfo(name = "break")
  private int breakLength;

  @ColumnInfo(name = "spotify_playlist_key", index = true)
  private String playlistKey;

  private long userId;

  private Long taskTypeId;

  public long getTaskId() {
    return taskId;
  }

  public void setTaskId(long taskId) {
    this.taskId = taskId;
  }


  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }


  public Date getStart() {
    return start;
  }

  public void setStart(Date start) {
    this.start = start;
  }

  public Date getEnd() {
    return end;
  }

  public void setEnd(Date end) {
    this.end = end;
  }

  public int getBreakLength() {
    return breakLength;
  }

  public void setBreakLength(int breakLength) {
    this.breakLength = breakLength;
  }

  public String getPlaylistKey() {
    return playlistKey;
  }

  public void setPlaylistKey(String playlistKey) {
    this.playlistKey = playlistKey;
  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public Long getTaskTypeId() {
    return taskTypeId;
  }

  public void setTaskTypeId(Long taskTypeId) {
    this.taskTypeId = taskTypeId;
  }
}
