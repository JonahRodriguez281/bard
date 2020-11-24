package edu.cnm.deepdive.bard.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.bard.model.entity.User;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.List;

@Dao
public interface UserDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  Single<Long> insert(User user);

  @Update
  Single<Integer> update(User User);

  @Delete
  Single<Integer> delete(User User);

  @Query("SELECT * FROM User WHERE user_id = :userId")
  LiveData<User> getById(long userId);

  @Query("SELECT * FROM User WHERE account_name = :accountName")
  LiveData<List<User>> getByName(String accountName);

  @Query("SELECT * FROM User WHERE oauth_key = :oauthKey")
  Maybe<User> getByOauthKey(String oauthKey);


}
