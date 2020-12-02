package edu.cnm.deepdive.bard.controller.ui.task;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.bard.R;
import edu.cnm.deepdive.bard.adapter.TaskAdapter;
import edu.cnm.deepdive.bard.adapter.TaskTypeAdapter;
import edu.cnm.deepdive.bard.controller.ui.session.SessionViewModel;
import edu.cnm.deepdive.bard.databinding.FragmentTaskTypeBinding;
import edu.cnm.deepdive.bard.model.entity.Task;
import edu.cnm.deepdive.bard.model.entity.TaskType;

/**
 * Fragment for the current Session
 */
public class TaskTypeFragment extends Fragment {

  private TaskTypeViewModel taskTypeViewModel;
  private FragmentTaskTypeBinding binding;

  /**
   * Method for what to display when the Fragment is created for the first time.
   *
   * @param inflater           Inflater for inflating the ViewBinding that will be displayed
   * @param container          Parent of the ViewGroup
   * @param savedInstanceState Saved state of the view
   * @return View displayed when fragment is first created
   */
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentTaskTypeBinding.inflate(getLayoutInflater());
    //noinspection ConstantConditions
    binding.addTaskTypeFab.setOnClickListener((v) ->
        Navigation.findNavController(getView()).navigate(TaskTypeFragmentDirections.openDialog(0)));
    binding.taskList.setOnItemClickListener((parent, view, position, id) -> {
      Task task = (Task) parent.getItemAtPosition(position);
      Toast.makeText(getContext(), task.getTaskName(), Toast.LENGTH_LONG).show();
    });
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupViewModel();
  }

  /**
   * Method for providing the {@link TaskTypeViewModel} as the ViewModel used for this fragment
   */
  public void setupViewModel() {
    FragmentActivity activity = getActivity();
    taskTypeViewModel = new ViewModelProvider(this).get(TaskTypeViewModel.class);
    getLifecycle().addObserver(taskTypeViewModel);
    taskTypeViewModel.getTaskTypes().observe(getViewLifecycleOwner(), (taskTypes) -> {
      //noinspection ConstantConditions
      TaskTypeAdapter adapter = new TaskTypeAdapter(
          activity, taskTypes,
          (taskType) -> Navigation.findNavController(
              getView()).navigate(TaskTypeFragmentDirections.openDialog(taskType.getTaskTypeId())),
          taskTypeViewModel::create,
          taskTypeViewModel::delete
      );
      binding.taskList.setAdapter(adapter);
    });
    taskTypeViewModel.getThrowable().observe(getViewLifecycleOwner(), (throwable) -> {
          if (throwable != null) {
            Log.e(getClass().getSimpleName(), throwable.getMessage(), throwable);
          }
        }
    );
  }
}