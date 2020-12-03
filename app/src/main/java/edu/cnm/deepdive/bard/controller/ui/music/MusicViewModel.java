package edu.cnm.deepdive.bard.controller.ui.music;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.bard.model.entity.Song;
import edu.cnm.deepdive.bard.service.SongRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

/**
 * Framework for what to display in the Music Fragment
 */
public class MusicViewModel extends AndroidViewModel implements LifecycleObserver {

  private final SongRepository songRepository;
  private final MutableLiveData<Song> song;
  private final MutableLiveData<Throwable> throwable;
  private final CompositeDisposable pending;

  /**
   * Constructor for the MusicViewModel
   *
   * @param application The application context needed for the ViewModel
   */
  public MusicViewModel(@NonNull Application application) {
    super(application);
    songRepository = new SongRepository(application);
    song = new MutableLiveData<>();
    pending = new CompositeDisposable();
    throwable = new MutableLiveData<>();
    testService();
  }

  /**
   * Method used to test both the getTracks method, and the playSong method utilized by the service
   */
  @SuppressLint("CheckResult")
  public void testService() {
    songRepository.getTracks()
        .doAfterSuccess(
            (songs) -> Log.d(getClass().getSimpleName(), songs.size() + " songs received"))
        .flatMapCompletable((songs) -> songRepository.playSong(songs.get(0)))
        .subscribe(
            () -> {
            },
            throwable::postValue
        );
  }

  /**
   * Returns a Song LiveData Object
   */
  public MutableLiveData<Song> getSong() {
    return song;
  }

  /**
   * Returns a list of Song LiveData Objects
   */
  public LiveData<List<Song>> getSongs() {
    return songRepository.getSongs();
  }

  /**
   * Returns a Throwable LiveData Object
   */
  public MutableLiveData<Throwable> getThrowable() {
    return throwable;
  }
}