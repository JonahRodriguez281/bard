package edu.cnm.deepdive.bard.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.bard.model.entity.TaskType;
import edu.cnm.deepdive.bard.model.pojo.TaskTypeWithTasks;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

/**
 * The {@link TaskTypeDao} provides the CRUD (create, read, update, delete) functions, as well as
 * specialized queries for the repository.
 */
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
  @Transaction
  LiveData<TaskTypeWithTasks> getTaskTypeWithTasks(long taskTypeId);

  @Query("SELECT * FROM TaskType WHERE task_type_id = :taskTypeId")
  LiveData<TaskType> select(long taskTypeId);

  @Query("SELECT * FROM TaskType ORDER BY name ASC")
  LiveData<List<TaskType>> getAll();
}
