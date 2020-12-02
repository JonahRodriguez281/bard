package edu.cnm.deepdive.bard.controller;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.cnm.deepdive.bard.R;
import edu.cnm.deepdive.bard.service.SpotifySignInService;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationResponse;

/**
 * Activity for answering the response for authorization for login
 */
public class LoginResponseActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AuthorizationResponse response = AuthorizationResponse.fromIntent(getIntent());
    AuthorizationException ex = AuthorizationException.fromIntent(getIntent());
    Intent successIntent = new Intent(this, NavigationActivity.class)
        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    Intent failureIntent = new Intent(this, LoginActivity.class)
        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    SpotifySignInService.getInstance().completeSignIn(response, ex, successIntent, failureIntent);
  }
}