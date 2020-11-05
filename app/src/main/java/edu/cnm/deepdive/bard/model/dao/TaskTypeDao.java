package edu.cnm.deepdive.bard.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.bard.model.entity.TaskType;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface TaskTypeDao {

  @Insert
  Single<Long> insert(TaskType taskType);

  @Insert
  Single<List<Long>> insert(TaskType... taskType);

  @Insert
  Single<List<Long>> insert(Collection<TaskType> taskType);

  @Update
  Single<Integer> update(TaskType taskType);

  @Update
  Single<Integer> update(TaskType... taskType);

  @Update
  Single<Integer> update(Collection<TaskType> taskType);

  @Delete
  Single<Integer> delete(TaskType taskType);

  @Delete
  Single<Integer> delete(TaskType... taskType);

  @Delete
  Single<Integer> delete(Collection<TaskType> taskType);


  @Query("SELECT * FROM TaskType WHERE task_type_id = :taskTypeId")
  LiveData<List<TaskType>> select(long taskTypeId);
}