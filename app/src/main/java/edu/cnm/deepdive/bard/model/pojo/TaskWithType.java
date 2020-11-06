package edu.cnm.deepdive.bard.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.Relation;
import edu.cnm.deepdive.bard.model.entity.Task;
import edu.cnm.deepdive.bard.model.entity.TaskType;
import java.util.List;

public class TaskWithType extends Task {

  @NonNull
  @Relation(
      entity = TaskType.class,
      entityColumn = "task_type_id",
      parentColumn = "task_type_id"
  )
  private TaskType taskType;

  @NonNull
  public TaskType getTaskType() {
    return taskType;
  }

  public void setTaskType(@NonNull TaskType taskType) {
    this.taskType = taskType;
  }
}
