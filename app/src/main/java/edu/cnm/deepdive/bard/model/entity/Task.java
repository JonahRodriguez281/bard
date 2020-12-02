package edu.cnm.deepdive.bard.model.entity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.util.Date;

/**
 * Task entity stored in the database along with attributes, fields, foreign keys, and indices.
 */

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

  /**
   * Returns the id of a Task
   */
  public long getTaskId() {
    return taskId;
  }

  /**
   * Sets an id as the taskId
   */
  public void setTaskId(long taskId) {
    this.taskId = taskId;
  }

  /**
   * Returns a Task by name
   */
  @NonNull
  public String getTaskName() {
    return taskName;
  }

  /**
   * Sets a name as the taskName
   */
  public void setTaskName(@NonNull String taskName) {
    this.taskName = taskName;
  }

  /**
   * Returns the description of a Task
   */
  @NonNull
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description for a Task
   */
  public void setDescription(@NonNull String description) {
    this.description = description;
  }

  /**
   * Returns the duration of a Task
   */
  public int getDuration() {
    return duration;
  }

  /**
   * Sets the duration of a Task
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }

  /**
   * Returns the start time of a Task
   */
  @NonNull
  public Date getStart() {
    return start;
  }

  /**
   * Sets the start time for a Task
   */
  public void setStart(@NonNull Date start) {
    this.start = start;
  }

  /**
   * Returns the end time of a Task
   */
  public Date getEnd() {
    return end;
  }

  /**
   * Sets the end time for a Task
   */
  public void setEnd(Date end) {
    this.end = end;
  }

  /**
   * Returns the break length of a Task
   */
  public int getBreakLength() {
    return breakLength;
  }

  /**
   * Sets the break length for a Task
   *
   * @param breakLength
   */
  public void setBreakLength(int breakLength) {
    this.breakLength = breakLength;
  }

  /**
   * Returns the playlist key of a Task
   */
  public String getPlaylistKey() {
    return playlistKey;
  }

  /**
   * Sets the playlist key for a task
   */
  public void setPlaylistKey(String playlistKey) {
    this.playlistKey = playlistKey;
  }

  /**
   * Returns a User id of a Task
   */
  public Long getUserId() {
    return userId;
  }

  /**
   * Sets the User id for a Task
   */
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  /**
   * Returns the TaskType id of a Task
   */
  public Long getTaskTypeId() {
    return taskTypeId;
  }

  /**
   * Sets the TaskType id for a Task
   */
  public void setTaskTypeId(Long taskTypeId) {
    this.taskTypeId = taskTypeId;
  }

  @NonNull
  @Override
  public String toString() {
    return taskName;
  }


}
