package edu.cnm.deepdive.bard.controller.ui.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.bard.databinding.FragmentSessionBinding;

public class TaskFragment extends Fragment {

  private TaskViewModel taskViewModel;
  private FragmentSessionBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentSessionBinding.inflate(getLayoutInflater());
    return binding.getRoot();
  }

  public void setupViewModel() {
    FragmentActivity activity = getActivity();
    //noinspection ConstantConditions
    taskViewModel = new ViewModelProvider(activity).get(TaskViewModel.class);
    getLifecycle().addObserver(taskViewModel);
  }
}