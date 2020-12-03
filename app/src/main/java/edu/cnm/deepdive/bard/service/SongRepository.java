package edu.cnm.deepdive.bard.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.bard.model.dao.SongDao;
import edu.cnm.deepdive.bard.model.dto.Artist;
import edu.cnm.deepdive.bard.model.dto.TracksResponse.Item;
import edu.cnm.deepdive.bard.model.entity.Song;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repository for handling the interaction between the Song Dao and the Music ViewModel
 */
public class SongRepository {

  private final Context context;
  private final SongDao songDao;
  private final SpotifyServiceProxy serviceProxy;
  private final SpotifySignInService signInService;

  /**
   * Constructor for the Song Repository which instantiates the fields
   *
   * @param context Application context needed for the repository
   */
  public SongRepository(Context context) {
    this.context = context;
    songDao = BardDatabase.getInstance().getSongDao();
    serviceProxy = SpotifyServiceProxy.getInstance();
    signInService = SpotifySignInService.getInstance();
  }

  /**
   * Method for Saving Songs for the database
   *
   * @param song Song Object to be saved
   * @return A completable success or fail
   */
  public Completable save(Song song) {
    return (song.getSongId() == 0)
        ? songDao.insert(song)
        .doAfterSuccess(song::setSongId)
        .ignoreElement()
        : songDao.update(song)
            .ignoreElement();
  }

  /**
   * Method for Deleting Songs from the database
   *
   * @param song Song object to be deleted
   * @return A completable success or fail
   */
  public Completable delete(Song song) {
    return (song.getSongId() == 0)
        ? Completable.complete()
        : songDao.delete(song)
            .ignoreElement();
  }

  /**
   * Returns a Song by it's id
   */
  public LiveData<Song> getSongById(long songId) {
    return songDao.getSongById(songId);
  }

  /**
   * Returns all Songs
   */
  public LiveData<List<Song>> getSongs() {
    return songDao.getAll();
  }

  /**
   * Returns a list of Songs by a fragment of the name
   */
  public LiveData<List<Song>> getSongByName(String songNameFragment) {
    return songDao.getSongByName(
        String.format("%%%s%%", songNameFragment)); // %%%s%% used to create %...% for LIKE in SQL
  }

  /**
   * Returns a list of Songs by the Artist
   */
  public LiveData<List<Song>> getSongByArtist(String artistNameFragment) {
    return songDao.getSongByArtist(String.format("%%%s%%", artistNameFragment));
  }

  /**
   * Returns a list of Songs featured on an Album
   */
  public LiveData<List<Song>> getSongByAlbum(String albumNameFragment) {
    return songDao.getSongByAlbum(String.format("%%%s%%", albumNameFragment));
  }

  /**
   * Returns a list of Track objects from the Web Service
   */
  public Single<List<Song>> getTracks() {
    return signInService.refresh()
        .flatMap(serviceProxy::getTracks)
        .map((response) -> response.getItems().stream()
            .map(Item::getTrack)
            .peek((song) -> {
              song.setAlbum(song.getAlbumDto().getName());
              song.setArtist(
                  song.getArtists().stream()
                      .map(Artist::getName)
                      .collect(Collectors.joining(", "))
              );
            })
            .collect(Collectors.toList())
        )
        .subscribeOn(Schedulers.io());
  }

  /**
   * Method for testing tracks being added to the queue of a User's playback
   */
  public Completable playSong(Song song) {
    return signInService.refresh()
        .flatMapCompletable(
            (token) -> serviceProxy.addToQueue(token, "spotify:track:" + song.getSpotifyKey()))
        .subscribeOn(Schedulers.io());
  }
}

