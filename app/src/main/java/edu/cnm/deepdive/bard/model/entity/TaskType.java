package edu.cnm.deepdive.bard.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * TaskType entity stored in the database along with attributes, fields, foreign keys, and indices.
 */
@SuppressWarnings("NotNullFieldNotInitialized")
@Entity(
    indices = @Index(value = "name", unique = true)
)
public class TaskType {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "task_type_id")
  private long taskTypeId;

  private String name;

  @NonNull
  private String description;

  private int duration;

  /**
   * Returns the id of a TaskType
   */
  public long getTaskTypeId() {
    return taskTypeId;
  }

  /**
   * Sets the id for a TaskType
   */
  public void setTaskTypeId(long taskTypeId) {
    this.taskTypeId = taskTypeId;
  }

  /**
   * Returns the name of a TaskType
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name for a TaskType
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the descriptions of a TaskType
   */
  @NonNull
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description for a TaskType
   */
  public void setDescription(@NonNull String description) {
    this.description = description;
  }

  /**
   * Returns the duration of a TaskType
   */
  public int getDuration() {
    return duration;
  }

  /**
   * Sets the duration for a TaskType
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }
}
