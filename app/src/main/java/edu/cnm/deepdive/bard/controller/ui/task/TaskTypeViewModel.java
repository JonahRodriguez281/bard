package edu.cnm.deepdive.bard.controller.ui.task;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.preference.PreferenceManager;
import edu.cnm.deepdive.bard.model.entity.Song;
import edu.cnm.deepdive.bard.model.entity.Task;
import edu.cnm.deepdive.bard.model.entity.TaskType;
import edu.cnm.deepdive.bard.model.pojo.TaskWithType;
import edu.cnm.deepdive.bard.service.SongRepository;
import edu.cnm.deepdive.bard.service.TaskRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class TaskTypeViewModel extends AndroidViewModel implements LifecycleObserver {

  private final TaskRepository taskRepository;
  private final SharedPreferences preferences;
  private final MutableLiveData<Task> task;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  public TaskTypeViewModel(@NonNull Application application) {
    super(application);
    taskRepository = new TaskRepository(application);
    preferences = PreferenceManager.getDefaultSharedPreferences(application);
    task = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    pending = new CompositeDisposable();
  }

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

  public LiveData<Task> getTask() {
    return task;
  }

  public LiveData<List<TaskType>> getTaskTypes() {
    return taskRepository.getTaskTypes();
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }
}