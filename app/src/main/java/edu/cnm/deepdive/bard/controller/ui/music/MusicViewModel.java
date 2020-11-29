package edu.cnm.deepdive.bard.controller.ui.music;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import edu.cnm.deepdive.bard.model.entity.Song;
import edu.cnm.deepdive.bard.service.SongRepository;
import java.util.List;

public class MusicViewModel extends AndroidViewModel implements LifecycleObserver {

  private MutableLiveData<String> mText;

  private final SongRepository songRepository;
  private final MutableLiveData<Song> song;

  public MusicViewModel(@NonNull Application application) {
    super(application);
    songRepository = new SongRepository(application);
    song = new MutableLiveData<>();
    mText = new MutableLiveData<>();
    mText.setValue("This is the music fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}