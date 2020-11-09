package edu.cnm.deepdive.bard.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.bard.model.entity.Task;
import edu.cnm.deepdive.bard.model.pojo.TaskWithType;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface TaskDao {

  @Insert
  Single<Long> insert(Task task);

  @Insert
  Single<List<Long>> insert(Task... task);

  @Insert
  Single<List<Long>> insert(Collection<Task> task);

  @Update
  Single<Integer> update(Task task);

  @Update
  Single<Integer> update(Task... task);

  @Update
  Single<Integer> update(Collection<Task> task);

  @Delete
  Single<Integer> delete(Task task);

  @Delete
  Single<Integer> delete(Task... task);

  @Delete
  Single<Integer> delete(Collection<Task> task);


  @Query("SELECT * FROM Task WHERE task_id = :taskId")
  @Transaction
  LiveData<TaskWithType> getById(long taskId);

  @Query("SELECT * FROM Task WHERE task_name LIKE :taskNameFragment")
  @Transaction
  LiveData<List<TaskWithType>> getByName(String taskNameFragment);

  @Query("SELECT * FROM Task WHERE spotify_playlist_key = :spotifyPlaylistKey")
  LiveData<List<Task>> getByPlaylistKey(String spotifyPlaylistKey);
  }
