package edu.cnm.deepdive.bard.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.Relation;
import edu.cnm.deepdive.bard.model.entity.Task;
import edu.cnm.deepdive.bard.model.entity.TaskType;
import java.util.List;

@SuppressWarnings("NotNullFieldNotInitialized")
public class TaskTypeWithTasks extends TaskType {

  @NonNull
  @Relation(
      entity = Task.class,
      entityColumn = "task_type_id",
      parentColumn = "task_type_id"
  )
  private List<Task> tasks;

  @NonNull
  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(@NonNull List<Task> tasks) {
    this.tasks = tasks;
  }
}
