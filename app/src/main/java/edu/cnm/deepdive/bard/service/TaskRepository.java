package edu.cnm.deepdive.bard.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.bard.model.dao.TaskDao;
import edu.cnm.deepdive.bard.model.dao.TaskTypeDao;
import edu.cnm.deepdive.bard.model.entity.Task;
import edu.cnm.deepdive.bard.model.entity.TaskType;
import edu.cnm.deepdive.bard.model.pojo.TaskWithType;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;
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

  public Completable update(TaskType taskType) {
    return (
        (taskType.getTaskTypeId() == 0)
            ? taskTypeDao.insert(taskType)
            : taskTypeDao.update(taskType)
    )
        .ignoreElement()
        .subscribeOn(Schedulers.io());
  }

  public Completable create(TaskType taskType) {
    return Single.fromCallable(() -> {
      Task task = new Task();
      task.setTaskTypeId(taskType.getTaskTypeId());
      task.setTaskName(taskType.getName());
      task.setDescription(taskType.getDescription());
      task.setDuration(taskType.getDuration());
      task.setStart(new Date());
      return task;
    })
        .flatMap(task -> taskDao.insert(task)
            .map((id) -> {
              task.setTaskId(id);
              return task;
            })
        )
        .ignoreElement()
        .subscribeOn(Schedulers.io());
  }

  public Completable delete(Task task) {
    return (task.getTaskId() == 0)
        ? Completable.complete()
        : taskDao.delete(task)
            .ignoreElement()
            .subscribeOn(Schedulers.io());
  }

  public Completable delete(TaskType taskType) {
    return (
        (taskType.getTaskTypeId() == 0)
            ? Completable.complete()
            : taskTypeDao.delete(taskType)
                .ignoreElement()
    )
        .subscribeOn(Schedulers.io());
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

  public LiveData<List<TaskType>> getTaskTypes() {
    return taskTypeDao.getAll();
  }

  public LiveData<TaskType> getTaskType(long id) {
    return taskTypeDao.select(id);
  }
}
