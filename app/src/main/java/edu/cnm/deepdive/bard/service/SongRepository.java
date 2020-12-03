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
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Repository for Song Objects
 */
public class SongRepository {

  private final Context context;
  private final SongDao songDao;
  private final SpotifyServiceProxy serviceProxy;
  private final SpotifySignInService signInService;

  public SongRepository(Context context) {
    this.context = context;
    songDao = BardDatabase.getInstance().getSongDao();
    serviceProxy = SpotifyServiceProxy.getInstance();
    signInService = SpotifySignInService.getInstance();
  }

  public Completable save(Song song) {
    return (song.getSongId() == 0)
        ? songDao.insert(song)
        .doAfterSuccess(song::setSongId)
        .ignoreElement()
        : songDao.update(song)
            .ignoreElement();
  }

  public Completable delete(Song song) {
    return (song.getSongId() == 0)
        ? Completable.complete()
        : songDao.delete(song)
            .ignoreElement();
  }

  public LiveData<Song> getSongById(long songId) {
    return songDao.getSongById(songId);
  }

  public LiveData<List<Song>> getSongs() {
    return songDao.getAll();
  }

  public LiveData<List<Song>> getSongByName(String songNameFragment) {
    return songDao.getSongByName(
        String.format("%%%s%%", songNameFragment)); // %%%s%% used to create %...% for LIKE in SQL
  }

  public LiveData<List<Song>> getSongByArtist(String artistNameFragment) {
    return songDao.getSongByArtist(String.format("%%%s%%", artistNameFragment));
  }

  public LiveData<List<Song>> getSongByAlbum(String albumNameFragment) {
    return songDao.getSongByAlbum(String.format("%%%s%%", albumNameFragment));
  }

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

  public Completable playSong(Song song) {
    return signInService.refresh()
        .flatMapCompletable((token) -> serviceProxy.addToQueue(token, "spotify:track:" + song.getSpotifyKey()))
        .subscribeOn(Schedulers.io());
  }
}

