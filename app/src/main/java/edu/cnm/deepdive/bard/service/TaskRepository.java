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

/**
 * Repository for handling the interaction between the Task Dao and the Session and TaskType ViewModels
 */
public class TaskRepository {

  private final Context context;
  private final TaskDao taskDao;
  private final TaskTypeDao taskTypeDao;

  /**
   * Constructor for the Task Repository which instantiates the fields
   *
   * @param context Application context needed for the repository
   */
  public TaskRepository(Context context) {
    this.context = context;
    taskDao = BardDatabase.getInstance().getTaskDao();
    taskTypeDao = BardDatabase.getInstance().getTaskTypeDao();
  }

  /**
   * Method for Updating TaskTypes in the database
   *
   * @param taskType TaskType Object to be updated
   * @return A completable success or fail
   */
  public Completable update(TaskType taskType) {
    return (
        (taskType.getTaskTypeId() == 0)
            ? taskTypeDao.insert(taskType)
            : taskTypeDao.update(taskType)
    )
        .ignoreElement()
        .subscribeOn(Schedulers.io());
  }

  /**
   * Method for Creating a TaskType in the database
   *
   * @param taskType TaskType Object to be saved
   * @return A completable success or fail
   */
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

  /**
   * Method for Deleting Tasks from the database
   *
   * @param task Task object to be deleted
   * @return A completable success or fail
   */
  public Completable delete(Task task) {
    return (task.getTaskId() == 0)
        ? Completable.complete()
        : taskDao.delete(task)
            .ignoreElement()
            .subscribeOn(Schedulers.io());
  }

  /**
   * Method for Deleting TaskTypes from the database
   *
   * @param taskType TaskType object to be deleted
   * @return A completable success or fail
   */
  public Completable delete(TaskType taskType) {
    return (
        (taskType.getTaskTypeId() == 0)
            ? Completable.complete()
            : taskTypeDao.delete(taskType)
                .ignoreElement()
    )
        .subscribeOn(Schedulers.io());
  }

  /**
   * Returns a list of Tasks by a fragment of the name
   */
  public LiveData<List<TaskWithType>> searchTasks(String taskNameFragment) {
    return taskDao.getByName(String.format("%%%s%%", taskNameFragment)); // %...% for LIKE in SQL
  }

  /**
   * Returns all Tasks
   */
  public LiveData<List<TaskWithType>> getAll() {
    return taskDao.getAll();
  }

  /**
   * Returns a Task by it's id
   */
  public LiveData<TaskWithType> getTask(long taskId) {
    return taskDao.getById(taskId);
  }

  /**
   * Returns a List of Tasks by their Spotify Playlist Key
   */
  public LiveData<List<Task>> getByPlaylistKey(String spotifyPlaylistKey) {
    return taskDao.getByPlaylistKey(spotifyPlaylistKey);
  }

  /**
   * Returns a list of TaskTypes
   */
  public LiveData<List<TaskType>> getTaskTypes() {
    return taskTypeDao.getAll();
  }

  /**
   * Returns a TaskType by it's id
   */
  public LiveData<TaskType> getTaskType(long id) {
    return taskTypeDao.select(id);
  }
}
