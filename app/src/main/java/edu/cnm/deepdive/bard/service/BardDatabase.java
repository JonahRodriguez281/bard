package edu.cnm.deepdive.bard.service;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import edu.cnm.deepdive.bard.model.dao.SongDao;
import edu.cnm.deepdive.bard.model.dao.TaskDao;
import edu.cnm.deepdive.bard.model.dao.TaskTypeDao;
import edu.cnm.deepdive.bard.model.dao.UserDao;
import edu.cnm.deepdive.bard.model.entity.Song;
import edu.cnm.deepdive.bard.model.entity.Task;
import edu.cnm.deepdive.bard.model.entity.TaskType;
import edu.cnm.deepdive.bard.model.entity.User;
import edu.cnm.deepdive.bard.service.BardDatabase.Converters;
import java.util.Date;

@Database(
    entities = {Song.class, Task.class, TaskType.class, User.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class BardDatabase extends RoomDatabase {

  private static final String DB_NAME = "bard_db";

  private static Application context;

  public static void setContext(Application context) {
    BardDatabase.context = context;
  }

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
            .build();
  }

  public static class Converters {

    @TypeConverter
    public static Long dateToLong(Date value) {
      return (value != null) ? value.getTime() : null;
    }

    @TypeConverter
    public static Date longToDate(Long value) {
      return (value != null) ? new Date(value) : null;
    }


  }
}
