package edu.cnm.deepdive.bard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.cnm.deepdive.bard.R;
import edu.cnm.deepdive.bard.databinding.ItemTaskBinding;
import edu.cnm.deepdive.bard.model.entity.TaskType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TaskTypeAdapter extends ArrayAdapter<TaskType> {

  private final LayoutInflater inflater;
  private final List<TaskType> taskTypes;
  private final EditClickListener editClickListener;
  private final AddClickListener addClickListener;
  private final DeleteClickListener deleteClickListener;

  public TaskTypeAdapter(@NonNull Context context, List<TaskType> taskTypes,
      EditClickListener editClickListener,
      AddClickListener addClickListener,
      DeleteClickListener deleteClickListener) {
    super(context, R.layout.item_task, taskTypes);
    inflater = LayoutInflater.from(context);
    this.editClickListener = editClickListener;
    this.addClickListener = addClickListener;
    this.deleteClickListener = deleteClickListener;
    this.taskTypes = taskTypes;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    ItemTaskBinding binding = (convertView != null)
        ? ItemTaskBinding.bind(convertView)
        : ItemTaskBinding.inflate(inflater, parent, false);
    TaskType taskType = getItem(position);
    binding.taskName.setText(taskType.getName());
    binding.taskDescription.setText(taskType.getDescription());
    binding.taskDuration.setText(Integer.toString(taskType.getDuration()));
    binding.editTask.setOnClickListener((v) -> editClickListener.onClick(taskType));
    binding.addTask.setOnClickListener((v) -> addClickListener.onClick(taskType));
    binding.deleteTask.setOnClickListener((v) -> deleteClickListener.onClick(taskType));
    return binding.getRoot();
  }

  public interface EditClickListener {

    void onClick(TaskType taskType);
  }


  public interface AddClickListener {

    void onClick(TaskType taskType);
  }


  public interface DeleteClickListener {

    void onClick(TaskType taskType);
  }


}
