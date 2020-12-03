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
import java.util.List;

/**
 * Adapter for correctly displaying {@link TaskType}s in the {@link edu.cnm.deepdive.bard.controller.ui.task.TaskTypeFragment}
 */
public class TaskTypeAdapter extends ArrayAdapter<TaskType> {

  private final LayoutInflater inflater;
  private final EditClickListener editClickListener;
  private final AddClickListener addClickListener;
  private final DeleteClickListener deleteClickListener;

  /**
   * Constructor for the TaskTypeAdapter
   *
   * @param context             Relevant context for the constructor
   * @param taskTypes           List of TaskTypes to be adapted
   * @param editClickListener   ClickListener for Edit button
   * @param addClickListener    ClickListener for Add button
   * @param deleteClickListener ClickListener for Delete button
   */
  public TaskTypeAdapter(@NonNull Context context, List<TaskType> taskTypes,
      EditClickListener editClickListener,
      AddClickListener addClickListener,
      DeleteClickListener deleteClickListener) {
    super(context, R.layout.item_task, taskTypes);
    inflater = LayoutInflater.from(context);
    this.editClickListener = editClickListener;
    this.addClickListener = addClickListener;
    this.deleteClickListener = deleteClickListener;
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

  /**
   * Interface for the Edit button displayed for each task
   */
  public interface EditClickListener {

    void onClick(TaskType taskType);
  }

  /**
   * Interface for the Add button displayed for each task
   */
  public interface AddClickListener {

    void onClick(TaskType taskType);
  }

  /**
   * Interface for the Delete button displayed for each task
   */
  public interface DeleteClickListener {

    void onClick(TaskType taskType);
  }


}
