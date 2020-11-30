package edu.cnm.deepdive.bard.controller.ui.session;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;
import edu.cnm.deepdive.bard.model.entity.Song;
import edu.cnm.deepdive.bard.model.entity.Task;
import edu.cnm.deepdive.bard.model.pojo.TaskWithType;
import edu.cnm.deepdive.bard.service.TaskRepository;
import java.util.List;

public class SessionViewModel extends AndroidViewModel implements LifecycleObserver {

  private final TaskRepository taskRepository;
  private final SharedPreferences preferences;
  private final MutableLiveData<Task> task;
  private final MutableLiveData<Song> song;
  private final MutableLiveData<Throwable> throwable;

  public SessionViewModel(@NonNull Application application) {
    super(application);
    taskRepository = new TaskRepository(application);
    preferences = PreferenceManager.getDefaultSharedPreferences(application);
    task = new MutableLiveData<>();
    song = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
  }

  @SuppressLint("CheckResult")
  public void delete(Task task) {
    throwable.setValue(null);
    taskRepository.delete(task)
        .subscribe(
            () -> {},
            throwable::postValue
        );
  }

  public LiveData<List<TaskWithType>> getTasks() {
    return taskRepository.getAll();
  }

  public LiveData<Song> getSong() {
    return song;
  }

  public LiveData<Throwable> getThrowable() {
    return throwable;
  }
}