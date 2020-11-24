package edu.cnm.deepdive.bard.controller;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration.Builder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import edu.cnm.deepdive.bard.R;
import edu.cnm.deepdive.bard.databinding.ActivityNavigationBinding;

public class NavigationActivity extends AppCompatActivity {

  private ActivityNavigationBinding binding;
  private AppBarConfiguration appBarConfig;
  private NavController navController;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityNavigationBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    setSupportActionBar(binding.appBarLayout.toolbar);
    setupNavigation();

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
    // Inflate the menu; this adds items to the action bar if it is present.
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