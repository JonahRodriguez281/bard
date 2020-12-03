package edu.cnm.deepdive.bard.controller.ui.music;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.bard.R;
import edu.cnm.deepdive.bard.adapter.SongAdapter;
import edu.cnm.deepdive.bard.databinding.FragmentMusicBinding;
import org.jetbrains.annotations.NotNull;

public class MusicFragment extends Fragment {

  // TODO Implement data gathered from Spotify Endpoints
  private MusicViewModel musicViewModel;
  private FragmentMusicBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentMusicBinding.inflate(inflater);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupViewModel();
  }

  public void setupViewModel() {
    FragmentActivity activity = getActivity();
    musicViewModel = new ViewModelProvider(this).get(MusicViewModel.class);
    getLifecycle().addObserver(musicViewModel);
    musicViewModel.getSongs().observe(getViewLifecycleOwner(), (songs) -> {
      SongAdapter adapter = new SongAdapter(
          activity, songs);
      binding.songList.setAdapter(adapter);
    });
    musicViewModel.getThrowable().observe(getViewLifecycleOwner(), (throwable) -> {
      if (throwable != null) {
        Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
      }
    });
  }
}