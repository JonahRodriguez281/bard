package edu.cnm.deepdive.bard.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.bard.model.dao.TaskDao;
import edu.cnm.deepdive.bard.model.dao.TaskTypeDao;
import edu.cnm.deepdive.bard.model.entity.Task;
import edu.cnm.deepdive.bard.model.pojo.TaskWithType;
import io.reactivex.Completable;
import java.util.List;

public class TaskRepository {

  private final Context context;
  private final TaskDao taskDao;
  private final TaskTypeDao taskTypeDao;

  public TaskRepository(Context context) {
    this.context = context;
    taskDao = BardDatabase.getInstance().getTaskDao();
    taskTypeDao = BardDatabase.getInstance().getTaskTypeDao();
  }

  public Completable save(Task task) {
    return (task.getTaskId() == 0)
        ? taskDao.insert(task)
        .doAfterSuccess(task::setTaskId)
        .ignoreElement()
        : taskDao.update(task)
            .ignoreElement();
  }
  public Completable delete(Task task) {
    return (task.getTaskId() == 0)
        ? Completable.complete()
        : taskDao.delete(task)
            .ignoreElement();
  }

  public LiveData<List<TaskWithType>> searchTasks(String taskNameFragment) {
    return taskDao.getByName(String.format("%%%s%%", taskNameFragment)); // %...% for LIKE in SQL
  }

  public LiveData<List<TaskWithType>> getAll() {
    return taskDao.getAll();
  }

  public LiveData<TaskWithType> getTask(long taskId) {
    return taskDao.getById(taskId);
  }

  public LiveData<List<Task>> getByPlaylistKey(String spotifyPlaylistKey) {
    return taskDao.getByPlaylistKey(spotifyPlaylistKey);
  }
}
