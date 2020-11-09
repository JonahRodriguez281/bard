package edu.cnm.deepdive.bard.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.bard.model.dao.UserDao;
import edu.cnm.deepdive.bard.model.entity.User;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import java.util.List;

/* User repository will change and become more elaborate based on Spotify Sign-In Service
incorporating bearer tokens and authorization. To be more modeled after Codebreaker User
repository. */

public class UserRepository {

  private final Context context;
  private final UserDao userDao;

  public UserRepository(Context context) {
    this.context = context;
    userDao = BardDatabase.getInstance().getUserDao();
  }
  public Completable save(User user) {
    return (user.getUserId() == 0)
        ? userDao.insert(user)
        .doAfterSuccess(user::setUserId)
        .ignoreElement()
        : userDao.update(user)
            .ignoreElement();
  }
  public Completable delete(User user) {
    return (user.getUserId() == 0)
        ? Completable.complete()
        : userDao.delete(user)
            .ignoreElement();
  }

  LiveData<User> selectById(long userId) {
    return userDao.getById(userId);
  }

  LiveData<List<User>> selectByName(String accountName) {
    return userDao.getByName(accountName);
  }

  Maybe<User> selectByOauthKey(String oauthKey) {
    return userDao.getByOauthKey(oauthKey);
  }
}
