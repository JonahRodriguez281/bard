package edu.cnm.deepdive.bard.controller.ui.task;

import android.app.Application;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.preference.PreferenceManager;
import edu.cnm.deepdive.bard.model.entity.Song;
import edu.cnm.deepdive.bard.model.entity.Task;
import edu.cnm.deepdive.bard.service.SongRepository;
import edu.cnm.deepdive.bard.service.TaskRepository;

public class TaskViewModel extends ViewModel implements LifecycleObserver {

  private final TaskRepository taskRepository;
  private final SharedPreferences preferences;
  private final MutableLiveData<Task> task;
  private MutableLiveData<String> mText;

  public TaskViewModel(@NonNull Application application) {
    taskRepository = new TaskRepository(application);
    preferences = PreferenceManager.getDefaultSharedPreferences(application);
    task = new MutableLiveData<>();
    mText = new MutableLiveData<>();
    mText.setValue("This is the session fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }


  public MutableLiveData<Task> getTask() {
    return task;
  }

}