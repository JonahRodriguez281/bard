package edu.cnm.deepdive.bard.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.cnm.deepdive.bard.R;
import edu.cnm.deepdive.bard.databinding.ActivityLoginBinding;
import edu.cnm.deepdive.bard.service.SpotifySignInService;

/**
 * Activity used to Login to the app and refresh ID tokens to stay logged in
 */
public class LoginActivity extends AppCompatActivity {

  private ActivityLoginBinding binding;

  @SuppressLint("CheckResult")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SpotifySignInService service = SpotifySignInService.getInstance();
    //noinspection ResultOfMethodCallIgnored
    service.refresh()
        .subscribe(
            (idToken) -> {
              Intent intent = new Intent(this, NavigationActivity.class)
                  .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
              startActivity(intent);
            },
            (throwable) -> {
              binding = ActivityLoginBinding.inflate(getLayoutInflater());
              setContentView(binding.getRoot());
              binding.signIn.setOnClickListener((v) -> {
                service.startSignIn(this,1000, LoginResponseActivity.class, getClass());
              });
            }
        );
  }
}