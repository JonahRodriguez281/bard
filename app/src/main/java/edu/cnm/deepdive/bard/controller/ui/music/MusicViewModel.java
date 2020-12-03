package edu.cnm.deepdive.bard.controller.ui.music;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;
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
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class MusicViewModel extends AndroidViewModel implements LifecycleObserver {

  private final SongRepository songRepository;
  private final MutableLiveData<Song> song;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  public MusicViewModel(@NonNull Application application) {
    super(application);
    songRepository = new SongRepository(application);
    song = new MutableLiveData<>();
    pending = new CompositeDisposable();
    throwable = new MutableLiveData<>();
    testService();
  }

  @SuppressLint("CheckResult")
  private void testService() {
    songRepository.getTracks()
        .doAfterSuccess((songs) -> Log.d(getClass().getSimpleName(), songs.size() + " songs received"))
        .flatMapCompletable((songs) -> songRepository.playSong(songs.get(0)))
        .subscribe(
            () -> {},
            throwable::postValue
        );
  }

  public MutableLiveData<Song> getSong() {
    return song;
  }

  public LiveData<List<Song>> getSongs() {
    return songRepository.getSongs();
  }

  public MutableLiveData<Throwable> getThrowable() {
    return throwable;
  }
}