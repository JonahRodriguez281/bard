package edu.cnm.deepdive.bard.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.bard.model.entity.Song;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface SongDao {

  @Insert
  Single<Long> insert(Song song);

  @Insert
  Single<List<Long>> insert(Song... songs);

  @Insert
  Single<List<Long>> insert(Collection<Song> songs);

  @Update
  Single<Integer> update(Song song);

  @Update
  Single<Integer> update(Song... songs);

  @Update
  Single<Integer> update(Collection<Song> songs);

  @Delete
  Single<Integer> delete(Song song);

  @Delete
  Single<Integer> delete(Song... songs);

  @Delete
  Single<Integer> delete(Collection<Song> songs);

  @Query("SELECT * FROM Song WHERE song_id = :songId ORDER BY song_name DESC")
  LiveData<List<Song>> getSongByName(long songId);

  @Query("SELECT * FROM Song WHERE song_id = :songId ORDER BY artist DESC")
  LiveData<List<Song>> getSongByArtist(long songId);

  @Query("SELECT * FROM Song WHERE song_id = :songId ORDER BY album DESC")
  LiveData<List<Song>> getSongByAlbum(long songId);
}
