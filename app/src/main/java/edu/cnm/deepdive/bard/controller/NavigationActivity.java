package edu.cnm.deepdive.bard.controller;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.AppBarConfiguration.Builder;
import androidx.navigation.ui.NavigationUI;
import edu.cnm.deepdive.bard.R;
import edu.cnm.deepdive.bard.databinding.ActivityNavigationBinding;
import edu.cnm.deepdive.bard.service.SpotifyServiceProxy;
import edu.cnm.deepdive.bard.service.SpotifySignInService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Activity used for Navigation through the app, using the Navigate Framework
 */
public class NavigationActivity extends AppCompatActivity {

  private ActivityNavigationBinding binding;
  private AppBarConfiguration appBarConfig;
  private NavController navController;

  @SuppressLint("CheckResult")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityNavigationBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    setSupportActionBar(binding.appBarLayout.toolbar);
    setupNavigation();
    //noinspection ResultOfMethodCallIgnored
    SpotifySignInService.getInstance().refresh()
        .observeOn(Schedulers.io())
        .flatMap((token) -> SpotifyServiceProxy.getInstance().getProfile(token))
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            (user) -> Log.d(getClass().getSimpleName(), user.getAccountName()),
            (throwable) -> Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show()
        );
  }

  private void setupNavigation() {
    appBarConfig = new Builder(R.id.nav_session, R.id.nav_music,
        R.id.nav_task)
        .setOpenableLayout(binding.drawerLayout)
        .build();
    //noinspection ConstantConditions
    navController = ((NavHostFragment) getSupportFragmentManager()
        .findFragmentById(R.id.nav_host_fragment)).getNavController();
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfig);
    NavigationUI.setupWithNavController(binding.navView, navController);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.settings, menu);
    return true;
  }

  @Override
  public boolean onSupportNavigateUp() {
    NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    return NavigationUI.navigateUp(navController, appBarConfig)
        || super.onSupportNavigateUp();
  }
}