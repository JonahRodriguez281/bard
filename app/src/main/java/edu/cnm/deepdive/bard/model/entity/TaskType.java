package edu.cnm.deepdive.bard.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

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

  @NonNull
  private int duration;

  public long getTaskTypeId() {
    return taskTypeId;
  }

  public void setTaskTypeId(long taskTypeId) {
    this.taskTypeId = taskTypeId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
}
