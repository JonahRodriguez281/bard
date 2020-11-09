package edu.cnm.deepdive.bard.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.bard.model.dao.SongDao;
import edu.cnm.deepdive.bard.model.entity.Song;
import io.reactivex.Completable;
import java.util.List;

public class SongRepository {

  private final Context context;
  private final SongDao songDao;

  public SongRepository(Context context) {
    this.context = context;
    songDao = BardDatabase.getInstance().getSongDao();
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

  LiveData<Song> getSongById(long songId) {
    return songDao.getSongById(songId);
  }

  LiveData<List<Song>> getSongByName(String songNameFragment) {
    return songDao.getSongByName(String.format("%%%s%%", songNameFragment)); // %%%s%% used to create %...% for LIKE in SQL
  }

  LiveData<List<Song>> getSongByArtist(String artistNameFragment) {
    return songDao.getSongByArtist(String.format("%%%s%%", artistNameFragment));
  }

  LiveData<List<Song>> getSongByAlbum(String albumNameFragment) {
    return songDao.getSongByAlbum(String.format("%%%s%%", albumNameFragment));
  }
}

