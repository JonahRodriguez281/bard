package edu.cnm.deepdive.bard.controller.ui.task;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.preference.PreferenceManager;
import edu.cnm.deepdive.bard.model.entity.Task;
import edu.cnm.deepdive.bard.model.entity.TaskType;
import edu.cnm.deepdive.bard.service.TaskRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

/**
 * Framework for what to display in the Session Fragment
 */
public class TaskTypeViewModel extends AndroidViewModel implements LifecycleObserver {

  private final TaskRepository taskRepository;
  private final SharedPreferences preferences;
  private final MutableLiveData<Task> task;
  private final MutableLiveData<Throwable> throwable;
  private final LiveData<TaskType> taskType;
  private final MutableLiveData<Long> taskTypeId;
  private final CompositeDisposable pending;

  /**
   * Constructor for the SessionViewModel
   *
   * @param application The application context needed for the ViewModel
   */
  public TaskTypeViewModel(@NonNull Application application) {
    super(application);
    taskRepository = new TaskRepository(application);
    preferences = PreferenceManager.getDefaultSharedPreferences(application);
    task = new MutableLiveData<>();
    taskTypeId = new MutableLiveData<>();
    taskType = Transformations.switchMap(taskTypeId, taskRepository::getTaskType);
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

  /**
   * Method for Updating a {@link TaskType}
   *
   * @param taskType The TaskType to be updated
   */
  @SuppressLint("CheckResult")
  public void update(TaskType taskType) {
    throwable.setValue(null);
    pending.add(
        taskRepository.update(taskType)
            .subscribe(
                () -> {
                },
                throwable::postValue
            )
    );
  }

  /**
   * Method for Deleting a {@link TaskType}
   *
   * @param taskType The TaskType to be deleted
   */
  @SuppressLint("CheckResult")
  public void delete(TaskType taskType) {
    throwable.setValue(null);
    pending.add(
        taskRepository.delete(taskType)
            .subscribe(
                () -> {
                },
                throwable::postValue
            )
    );
  }

  /**
   * Method for Creating a new {@link TaskType}
   *
   * @param taskType The TaskType to be created
   */
  @SuppressLint("CheckResult")
  public void create(TaskType taskType) {
    throwable.setValue(null);
    pending.add(
        taskRepository.create(taskType)
            .subscribe(
                () -> {
                },
                throwable::postValue
            )
    );

  }

  /**
   * Returns a single Task
   */
  public LiveData<Task> getTask() {
    return task;
  }

  /**
   * Returns all Tasks with TaskTypes
   */
  public LiveData<List<TaskType>> getTaskTypes() {
    return taskRepository.getTaskTypes();
  }

  /**
   * Returns a single TaskType
   */
  public LiveData<TaskType> getTaskType() {
    return taskType;
  }

  /**
   * Sets an id as the id for a specific TaskType
   */
  public void setTaskTypeId(long id) {
    taskTypeId.setValue(id);
  }

  /**
   * Getter for returning a Throwable
   */
  public LiveData<Throwable> getThrowable() {
    return throwable;
  }

}