package edu.cnm.deepdive.bard.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.bard.model.dao.UserDao;
import edu.cnm.deepdive.bard.model.entity.Song;
import edu.cnm.deepdive.bard.model.entity.User;
import io.reactivex.Completable;
import io.reactivex.Maybe;

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

  LiveData<User> selectByUser(long userId) {
    return userDao.selectUser(userId);
  }

  Maybe<User> selectByOauthKey(String oauthKey) {
    return userDao.selectByOauthKey(oauthKey);
  }
}
