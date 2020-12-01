package edu.cnm.deepdive.bard.controller.ui.session;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.bard.R;
import edu.cnm.deepdive.bard.adapter.TaskAdapter;
import edu.cnm.deepdive.bard.databinding.FragmentSessionBinding;

public class SessionFragment extends Fragment {

  private SessionViewModel sessionViewModel;
  private FragmentSessionBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentSessionBinding.inflate(getLayoutInflater());
    setupViews();
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupViewModel();
  }

  public void setupViews() {
    //noinspection ConstantConditions
    binding.addTaskFab.setOnClickListener((view) -> Navigation.findNavController(getView())
    .navigate(R.id.action_nav_session_to_nav_task));
  }

  public void setupViewModel() {
    FragmentActivity activity = getActivity();
    sessionViewModel = new ViewModelProvider(this).get(SessionViewModel.class);
    getLifecycle().addObserver(sessionViewModel);
    sessionViewModel.getTasks().observe(getViewLifecycleOwner(), (tasks) -> {
      //noinspection ConstantConditions
      TaskAdapter adapter = new TaskAdapter(
          activity, tasks,
          sessionViewModel::delete);
      binding.currentTaskList.setAdapter(adapter);
    });
    sessionViewModel.getThrowable().observe(getViewLifecycleOwner(), (throwable) -> {
      if (throwable != null) {
        Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
      }
    });
  }
}