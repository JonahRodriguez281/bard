package edu.cnm.deepdive.bard.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * User entity stored in the database along with attributes, fields, foreign keys, and indices.
 */
@SuppressWarnings("NotNullFieldNotInitialized")
@Entity(
    indices = {
        @Index(value = "oauth_key", unique = true)
    }
)
public class User {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "user_id")
  private long userId;

  @NonNull
  @ColumnInfo(name = "oauth_key")
  private String oauthKey;

  @NonNull
  @ColumnInfo(name = "account_name", index = true)
  @Expose
  @SerializedName("id")
  private String accountName;

  /**
   * Returns the id of a User
   */
  public long getUserId() {
    return userId;
  }

  /**
   * Sets the id for a User
   */
  public void setUserId(long userId) {
    this.userId = userId;
  }

  /**
   * Returns the OauthKey of a User
   */
  @NonNull
  public String getOauthKey() {
    return oauthKey;
  }

  /**
   * Sets the OauthKey for a User
   */
  public void setOauthKey(@NonNull String oauthKey) {
    this.oauthKey = oauthKey;
  }

  /**
   * Returns the account name of a User
   */
  @NonNull
  public String getAccountName() {
    return accountName;
  }

  /**
   * Sets the account name for a User
   */
  public void setAccountName(@NonNull String accountName) {
    this.accountName = accountName;
  }
}
