package edu.cnm.deepdive.bard.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Date;

@SuppressWarnings("NotNullFieldNotInitialized")
@Entity(
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

  @NonNull
  @ColumnInfo(name = "task_name")
  private String taskName;

  @NonNull
  private String description;

  private int duration;

  @NonNull
  @ColumnInfo(index = true)
  private Date start;

  @NonNull
  @ColumnInfo(index = true)
  private Date end;

  @ColumnInfo(name = "break")
  private int breakLength;

  @ColumnInfo(name = "spotify_playlist_key", index = true)
  private String playlistKey;

  @ColumnInfo(name = "user_id", index = true)
  private long userId;

  @ColumnInfo(name = "task_type_id", index = true)
  private long taskTypeId;

  public long getTaskId() {
    return taskId;
  }

  public void setTaskId(long taskId) {
    this.taskId = taskId;
  }

  @NonNull
  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(@NonNull String taskName) {
    this.taskName = taskName;
  }

  @NonNull
  public String getDescription() {
    return description;
  }

  public void setDescription(@NonNull String description) {
    this.description = description;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  @NonNull
  public Date getStart() {
    return start;
  }

  public void setStart(@NonNull Date start) {
    this.start = start;
  }

  @NonNull
  public Date getEnd() {
    return end;
  }

  public void setEnd(@NonNull Date end) {
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

  public long getTaskTypeId() {
    return taskTypeId;
  }

  public void setTaskTypeId(long taskTypeId) {
    this.taskTypeId = taskTypeId;
  }
}
