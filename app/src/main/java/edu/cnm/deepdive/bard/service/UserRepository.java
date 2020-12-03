package edu.cnm.deepdive.bard.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.bard.model.dao.UserDao;
import edu.cnm.deepdive.bard.model.entity.User;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import java.util.List;

/**
 * Repository for handling the interaction between the Task Dao and the Session and TaskType ViewModels
 */
public class UserRepository {

  private final Context context;
  private final UserDao userDao;
  private final SpotifySignInService signInService;
  private final SpotifyServiceProxy webService;

  /**
   * Constructor for the User Repository which instantiates the fields
   *
   * @param context Application context needed for the repository
   */
  public UserRepository(Context context) {
    this.context = context;
    userDao = BardDatabase.getInstance().getUserDao();
    signInService = SpotifySignInService.getInstance();
    webService = SpotifyServiceProxy.getInstance();
  }

  /**
   * Method for Saving User to the database
   *
   * @param user User Object to be saved
   * @return A completable success or fail
   */
  public Completable save(User user) {
    return (user.getUserId() == 0)
        ? userDao.insert(user)
        .doAfterSuccess(user::setUserId)
        .ignoreElement()
        : userDao.update(user)
            .ignoreElement();
  }

  /**
   * Method for Deleting Users from the database
   *
   * @param user User object to be deleted
   * @return A completable success or fail
   */
  public Completable delete(User user) {
    return (user.getUserId() == 0)
        ? Completable.complete()
        : userDao.delete(user)
            .ignoreElement();
  }

  /**
   * Returns a User by it's id
   */
  LiveData<User> selectById(long userId) {
    return userDao.getById(userId);
  }

  /**
   * Returns a list of Users by name
   */
  LiveData<List<User>> selectByName(String accountName) {
    return userDao.getByName(accountName);
  }

  /**
   * Returns a User by it's Oauth Key
   */
  Maybe<User> selectByOauthKey(String oauthKey) {
    return userDao.getByOauthKey(oauthKey);
  }
}
