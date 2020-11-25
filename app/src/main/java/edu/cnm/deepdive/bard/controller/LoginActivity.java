package edu.cnm.deepdive.bard.controller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.cnm.deepdive.bard.R;
import edu.cnm.deepdive.bard.databinding.ActivityLoginBinding;
import edu.cnm.deepdive.bard.service.SpotifySignInService;

public class LoginActivity extends AppCompatActivity {

  private ActivityLoginBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityLoginBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    binding.signIn.setOnClickListener((v) -> {
      SpotifySignInService service = SpotifySignInService.getInstance();
      service.startSignIn(this,1000, LoginResponseActivity.class, getClass());
    });
  }
}