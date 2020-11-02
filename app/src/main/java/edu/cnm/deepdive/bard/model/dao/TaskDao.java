package edu.cnm.deepdive.bard.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;
import edu.cnm.deepdive.bard.model.entity.Task;
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


}
