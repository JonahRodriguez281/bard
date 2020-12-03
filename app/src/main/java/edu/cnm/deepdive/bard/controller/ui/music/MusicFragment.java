package edu.cnm.deepdive.bard.controller.ui.music;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.bard.adapter.SongAdapter;
import edu.cnm.deepdive.bard.databinding.FragmentMusicBinding;

/**
 * Fragment for the Music pulled from the Spotify Service
 */
public class MusicFragment extends Fragment {

  private MusicViewModel musicViewModel;
  private FragmentMusicBinding binding;

  @Override
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

  /**
   * Method for providing the {@link MusicViewModel} as the ViewModel used for this fragment
   */
  public void setupViewModel() {
    FragmentActivity activity = getActivity();
    musicViewModel = new ViewModelProvider(this).get(MusicViewModel.class);
    getLifecycle().addObserver(musicViewModel);
    musicViewModel.testService();
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