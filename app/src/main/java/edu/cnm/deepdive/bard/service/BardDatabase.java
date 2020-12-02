package edu.cnm.deepdive.bard.service;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import edu.cnm.deepdive.bard.model.dao.SongDao;
import edu.cnm.deepdive.bard.model.dao.TaskDao;
import edu.cnm.deepdive.bard.model.dao.TaskTypeDao;
import edu.cnm.deepdive.bard.model.dao.UserDao;
import edu.cnm.deepdive.bard.model.entity.Song;
import edu.cnm.deepdive.bard.model.entity.Task;
import edu.cnm.deepdive.bard.model.entity.TaskType;
import edu.cnm.deepdive.bard.model.entity.User;
import edu.cnm.deepdive.bard.service.BardDatabase.Converters;
import io.reactivex.schedulers.Schedulers;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * Class representing the Database. Implements the DAO classes and creates an Instance of the Bard
 * Database
 */
@Database(
    entities = {Song.class, Task.class, TaskType.class, User.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class BardDatabase extends RoomDatabase {

  private static final String DB_NAME = "bard_db";

  private static Application context;

  public static void setContext(Application context) {
    BardDatabase.context = context;
  }

  /**
   * Creates an Instance of the Database
   *
   * @return The Bard Database
   */
  public static BardDatabase getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public abstract SongDao getSongDao();

  public abstract TaskDao getTaskDao();

  public abstract TaskTypeDao getTaskTypeDao();

  public abstract UserDao getUserDao();

  private static class InstanceHolder {

    private static final BardDatabase INSTANCE =
        Room.databaseBuilder(context, BardDatabase.class, DB_NAME)
            .addCallback(new Callback())
            .build();
  }

  private static class Callback extends RoomDatabase.Callback {

    @SuppressLint("CheckResult")
    @Override
    public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
      super.onCreate(db);
      List<TaskType> taskTypes = new LinkedList<>();
      for (String name : new String[]{"TaskType A", "TaskType B", "TaskType C"}) {
        TaskType taskType = new TaskType();
        taskType.setName(name);
        taskType.setDescription(name);
        taskType.setDuration(10);
        taskTypes.add(taskType);
      }
      BardDatabase.getInstance().getTaskTypeDao().insert(taskTypes)
          .subscribeOn(Schedulers.io())
          .subscribe(
              (ids) -> {
              },
              (throwable) -> Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable)
          );
    }
  }

  /**
   * Class holding Converter Methods for Various data types
   */
  public static class Converters {

    /**
     * Converts a Date object to a Long
     *
     * @param value Date being converted
     * @return A new Long Value
     */
    @TypeConverter
    public static Long dateToLong(Date value) {
      return (value != null) ? value.getTime() : null;
    }

    /**
     * Converts a Long into a Date Object
     *
     * @param value Long being converted
     * @return A new Date object
     */
    @TypeConverter
    public static Date longToDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }
  }
}
