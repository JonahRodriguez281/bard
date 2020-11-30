package edu.cnm.deepdive.bard.controller.ui.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.bard.databinding.FragmentSessionBinding;
import edu.cnm.deepdive.bard.databinding.FragmentTaskBinding;
import edu.cnm.deepdive.bard.model.entity.Task;
import org.jetbrains.annotations.NotNull;

public class TaskFragment extends Fragment {

  private TaskViewModel taskViewModel;
  private FragmentTaskBinding binding;

  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentTaskBinding.inflate(getLayoutInflater());
    binding.taskList.setOnItemClickListener((parent, view, position, id) -> {
      Task task = (Task) parent.getItemAtPosition(position);
      Toast.makeText(getContext(), task.getTaskName(), Toast.LENGTH_LONG).show();
    });
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull @NotNull View view,
      @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setupViewModel();
  }

  public void setupViewModel() {
    FragmentActivity activity = getActivity();
    //noinspection ConstantConditions
    taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
    getLifecycle().addObserver(taskViewModel);
    taskViewModel.getTasks().observe(getViewLifecycleOwner(), (tasks) -> {
      ArrayAdapter<? extends Task> adapter = new ArrayAdapter<>(
          activity, android.R.layout.simple_list_item_1, tasks);
      binding.taskList.setAdapter(adapter);
    });
  }
}