package edu.cnm.deepdive.bard.controller.ui.task;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.bard.adapter.TaskAdapter;
import edu.cnm.deepdive.bard.adapter.TaskTypeAdapter;
import edu.cnm.deepdive.bard.databinding.FragmentTaskTypeBinding;
import edu.cnm.deepdive.bard.model.entity.Task;
import edu.cnm.deepdive.bard.model.entity.TaskType;

public class TaskTypeFragment extends Fragment {

  private TaskTypeViewModel taskTypeViewModel;
  private FragmentTaskTypeBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentTaskTypeBinding.inflate(getLayoutInflater());
    binding.taskList.setOnItemClickListener((parent, view, position, id) -> {
      Task task = (Task) parent.getItemAtPosition(position);
      Toast.makeText(getContext(), task.getTaskName(), Toast.LENGTH_LONG).show();
    });
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupViewModel();
  }

  public void setupViewModel() {
    FragmentActivity activity = getActivity();
    taskTypeViewModel = new ViewModelProvider(this).get(TaskTypeViewModel.class);
    getLifecycle().addObserver(taskTypeViewModel);
    taskTypeViewModel.getTaskTypes().observe(getViewLifecycleOwner(), (taskTypes) -> {
      //noinspection ConstantConditions
      TaskTypeAdapter adapter = new TaskTypeAdapter(
          activity, taskTypes,
          (taskType) -> Log.d(
              getClass().getSimpleName(), String.format("Editing %d", taskType.getTaskTypeId())),
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