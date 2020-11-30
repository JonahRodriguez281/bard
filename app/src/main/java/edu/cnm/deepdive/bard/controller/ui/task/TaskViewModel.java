package edu.cnm.deepdive.bard.controller.ui.task;

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
import edu.cnm.deepdive.bard.model.pojo.TaskWithType;
import edu.cnm.deepdive.bard.service.SongRepository;
import edu.cnm.deepdive.bard.service.TaskRepository;
import java.util.List;

public class TaskViewModel extends AndroidViewModel implements LifecycleObserver {

  private final TaskRepository taskRepository;
  private final SharedPreferences preferences;
  private final MutableLiveData<Task> task;

  public TaskViewModel(@NonNull Application application) {
    super(application);
    taskRepository = new TaskRepository(application);
    preferences = PreferenceManager.getDefaultSharedPreferences(application);
    task = new MutableLiveData<>();
  }

  public LiveData<Task> getTask() {
    return task;
  }

  public LiveData<List<TaskWithType>> getTasks() {
    return taskRepository.getAll();
  }

}