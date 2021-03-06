package edu.cnm.deepdive.bard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.cnm.deepdive.bard.R;
import edu.cnm.deepdive.bard.databinding.ItemCurrentTaskBinding;
import edu.cnm.deepdive.bard.model.entity.Task;
import edu.cnm.deepdive.bard.model.pojo.TaskWithType;
import java.util.List;

/**
 * Adapter for correctly displaying {@link Task}s in the {@link edu.cnm.deepdive.bard.controller.ui.session.SessionFragment}.
 */
public class TaskAdapter extends ArrayAdapter<TaskWithType> {

  private final LayoutInflater inflater;
  private final DeleteClickListener deleteClickListener;

  /**
   * Constructor for the TaskAdapter
   *
   * @param context             Relevant context for the constructor
   * @param currentTasks        List of Tasks to be adapted
   * @param deleteClickListener ClickListener for Delete button
   */
  public TaskAdapter(@NonNull Context context, List<TaskWithType> currentTasks,
      DeleteClickListener deleteClickListener) {
    super(context, R.layout.item_current_task, currentTasks);
    inflater = LayoutInflater.from(context);
    this.deleteClickListener = deleteClickListener;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    ItemCurrentTaskBinding binding = (convertView != null)
        ? ItemCurrentTaskBinding.bind(convertView)
        : ItemCurrentTaskBinding.inflate(inflater, parent, false);
    Task task = getItem(position);
    binding.currentTaskName.setText(task.getTaskName());
    binding.currentTaskDescription.setText(task.getDescription());
    binding.currentTaskDuration.setText(Integer.toString(task.getDuration()));
    binding.deleteCurrentTask.setOnClickListener((v) -> deleteClickListener.onClick(task));
    return binding.getRoot();
  }

  /**
   * Interface for the Delete button displayed for each task
   */
  public interface DeleteClickListener {

    void onClick(Task task);
  }
}
