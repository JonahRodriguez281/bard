package edu.cnm.deepdive.bard.controller.ui.music;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import edu.cnm.deepdive.bard.R;
import edu.cnm.deepdive.bard.databinding.FragmentMusicBinding;

public class MusicFragment extends Fragment {

  private MusicViewModel musicViewModel;
  private FragmentMusicBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater,
      ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentMusicBinding.inflate(inflater);
    musicViewModel = new ViewModelProvider(this).get(MusicViewModel.class);
    musicViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
      @Override
      public void onChanged(@Nullable String s) {
        binding.textMusic.setText(s);
      }
    });
    return binding.getRoot();
  }
}