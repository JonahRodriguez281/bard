package edu.cnm.deepdive.bard.controller.ui.task;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.bard.R;
import edu.cnm.deepdive.bard.databinding.FragmentTaskTypeDialogBinding;
import edu.cnm.deepdive.bard.model.entity.TaskType;
import org.jetbrains.annotations.NotNull;

public class TaskTypeDialogFragment extends DialogFragment implements TextWatcher {

  private FragmentTaskTypeDialogBinding binding;
  private AlertDialog dialog;
  private TaskTypeViewModel viewModel;
  private long id;
  private TaskType taskType;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //noinspection ConstantConditions
    id = TaskTypeDialogFragmentArgs.fromBundle(getArguments()).getTaskTypeId();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    binding = FragmentTaskTypeDialogBinding.inflate(
        LayoutInflater.from(getContext()), null, false);
    dialog = new Builder(getContext())
        .setIcon(R.drawable.ic_plus)
        .setTitle(R.string.create_task_type_properties_title)
        .setView(binding.getRoot())
        .setNeutralButton(android.R.string.cancel, (dlg, which) -> {})
        .setPositiveButton(android.R.string.ok, (dlg, which) -> create())
        .create();
    dialog.setOnShowListener((dlg) -> checkSubmitConditions());
    return dialog;
  }

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(TaskTypeViewModel.class);
    binding.name.addTextChangedListener(this);
    binding.description.addTextChangedListener(this);
    binding.duration.addTextChangedListener(this);
    if (id != 0) {
      viewModel.setTaskTypeId(id);
      viewModel.getTaskType().observe(getViewLifecycleOwner(), (taskType) -> {
        this.taskType = taskType;
        binding.name.setText(taskType.getName());
        binding.description.setText(taskType.getDescription());
        binding.duration.setText(String.valueOf(taskType.getDuration()));
      });
    } else {
      taskType = new TaskType();
    }
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {
  }

  @Override
  public void afterTextChanged(Editable s) {
    checkSubmitConditions();
  }

  private void checkSubmitConditions() {
    Button positive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
    //noinspection ConstantConditions
    positive.setEnabled(
        !binding.name.getText().toString().trim().isEmpty() &&
        !binding.description.getText().toString().trim().isEmpty() &&
        !binding.duration.getText().toString().trim().isEmpty());
  }

  @SuppressWarnings("ConstantConditions")
  private void create() {
    String name = binding.name.getText().toString().trim();
    String description = binding.description.getText().toString().trim();
    int duration = Integer.parseInt(binding.duration.getText().toString().trim());
    taskType.setName(name);
    taskType.setDescription(description);
    taskType.setDuration(duration);
    viewModel.update(taskType);
  }
}
