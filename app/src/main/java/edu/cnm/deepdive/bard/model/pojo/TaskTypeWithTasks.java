package edu.cnm.deepdive.bard.model.pojo;

import androidx.annotation.NonNull;
import androidx.room.Relation;
import edu.cnm.deepdive.bard.model.entity.Task;
import edu.cnm.deepdive.bard.model.entity.TaskType;
import java.util.List;

/**
 * POJO for TaskTypes with their Tasks
 */
@SuppressWarnings("NotNullFieldNotInitialized")
public class TaskTypeWithTasks extends TaskType {

  @NonNull
  @Relation(
      entity = Task.class,
      entityColumn = "task_type_id",
      parentColumn = "task_type_id"
  )
  private List<Task> tasks;

  /**
   * Returns a list of Tasks of the TaskType
   */
  @NonNull
  public List<Task> getTasks() {
    return tasks;
  }

  /**
   * Sets a List of Tasks for a TaskType
   */
  public void setTasks(@NonNull List<Task> tasks) {
    this.tasks = tasks;
  }
}
