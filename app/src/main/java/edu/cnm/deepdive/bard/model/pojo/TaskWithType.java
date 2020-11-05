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
  private List<TaskType> taskTypes;

  @NonNull
  public List<TaskType> getTaskTypes() {
    return taskTypes;
  }

  public void setTaskTypes(@NonNull List<TaskType> taskTypes) {
    this.taskTypes = taskTypes;
  }
}
