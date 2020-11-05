package edu.cnm.deepdive.bard.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import edu.cnm.deepdive.bard.model.entity.User;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface UserDao {

  @Insert
  Single<Long> insert(User user);

  @Update
  Single<Integer> update(User User);

  @Delete
  Single<Integer> delete(User User);

  @Query("SELECT * FROM User WHERE user_id = :userId")
  LiveData<User> getUser(long userId);

  @Query("SELECT * FROM User WHERE oauth_key = :oauthKey")
  Maybe<User> selectByOauthKey(String oauthKey);


}
