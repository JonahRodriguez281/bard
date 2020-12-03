package edu.cnm.deepdive.bard.controller.ui.session;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.bard.R;
import edu.cnm.deepdive.bard.adapter.TaskAdapter;
import edu.cnm.deepdive.bard.databinding.FragmentSessionBinding;


/**
 * Fragment for the current Session
 */
public class SessionFragment extends Fragment {

  private SessionViewModel sessionViewModel;
  private FragmentSessionBinding binding;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentSessionBinding.inflate(getLayoutInflater());
    setupViews();
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupViewModel();
  }

  /**
   * Method to instantiate the Floating Action Button once the base view has been created
   */
  public void setupViews() {
    //noinspection ConstantConditions
    binding.addTaskFab.setOnClickListener((view) -> Navigation.findNavController(getView())
        .navigate(R.id.action_nav_session_to_nav_task));
  }

  /**
   * Method for providing the {@link SessionViewModel} as the ViewModel used for this fragment
   */
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