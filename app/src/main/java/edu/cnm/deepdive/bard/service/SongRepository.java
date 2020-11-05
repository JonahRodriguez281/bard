package edu.cnm.deepdive.bard.service;

import android.content.Context;
import edu.cnm.deepdive.bard.model.dao.SongDao;
import edu.cnm.deepdive.bard.model.entity.Song;
import io.reactivex.Completable;

public class SongRepository {

  private final Context context;
  private final SongDao songDao;

  public SongRepository(Context context) {
    this.context = context;
    songDao = BardDatabase.getInstance().getSongDao();
  }

  private Completable save(Song song) {
    return (
        (song.getSongId() == 0)
            ? songDao.insert(song)
            .map((id) -> {
              song.setSongId(id);
              return song;
            })
            : songDao.update(song)
                .map((numRecords) -> song)
    )
        .ignoreElement(); // Changes Single to Completable
  }
  // What I'd expect a user to be able to do with the songs.
}
