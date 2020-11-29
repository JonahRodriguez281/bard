package edu.cnm.deepdive.bard.controller.ui.session;

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

public class SessionViewModel extends ViewModel implements LifecycleObserver {

  private final TaskRepository taskRepository;
  private final SongRepository songRepository;
  private final SharedPreferences preferences;
  private final MutableLiveData<Task> task;
  private final MutableLiveData<Song> song;
  private MutableLiveData<String> mText;

  public SessionViewModel(@NonNull Application application) {
    taskRepository = new TaskRepository(application);
    songRepository = new SongRepository(application);
    preferences = PreferenceManager.getDefaultSharedPreferences(application);
    task = new MutableLiveData<>();
    song = new MutableLiveData<>();
    mText = new MutableLiveData<>();
    mText.setValue("This is the session fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }


  public MutableLiveData<Task> getTask() {
    return task;
  }

  public MutableLiveData<Song> getSong() {
    return song;
  }
}