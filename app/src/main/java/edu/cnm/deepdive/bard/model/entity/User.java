package edu.cnm.deepdive.bard.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

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
  private String accountName;

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getOauthKey() {
    return oauthKey;
  }

  public void setOauthKey(String oauthKey) {
    this.oauthKey = oauthKey;
  }


  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }
}
