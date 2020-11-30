package edu.cnm.deepdive.bard.model.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
            onDelete = ForeignKey.SET_NULL
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

  @ColumnInfo(index = true)
  private Date end;

  @ColumnInfo(name = "break")
  private int breakLength;

  @ColumnInfo(name = "spotify_playlist_key", index = true)
  private String playlistKey;

  @ColumnInfo(name = "user_id", index = true)
  private Long userId;

  @ColumnInfo(name = "task_type_id", index = true)
  private Long taskTypeId;

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

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getTaskTypeId() {
    return taskTypeId;
  }

  public void setTaskTypeId(Long taskTypeId) {
    this.taskTypeId = taskTypeId;
  }

  @NonNull
  @Override
  public String toString() {
    return taskName;
  }


}
