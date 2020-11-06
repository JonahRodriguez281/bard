package edu.cnm.deepdive.bard.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.room.Query;
import edu.cnm.deepdive.bard.model.dao.TaskDao;
import edu.cnm.deepdive.bard.model.pojo.TaskWithType;
import java.util.List;

public class TaskRepository {

  private final Context context;
  private final TaskDao taskDao;

  public TaskRepository(Context context) {
    this.context = context;
    taskDao = BardDatabase.getInstance().getTaskDao();
  }

  LiveData<List<TaskWithType>> searchTasks(String taskNameFragment) {
    return taskDao.getByName(String.format("%%%s%%", taskNameFragment)); // %...% for LIKE in SQL
  }
}
