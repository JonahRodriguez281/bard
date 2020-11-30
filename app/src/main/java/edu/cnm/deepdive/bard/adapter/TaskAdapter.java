package edu.cnm.deepdive.bard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import edu.cnm.deepdive.bard.R;
import edu.cnm.deepdive.bard.databinding.ItemCurrentTaskBinding;
import edu.cnm.deepdive.bard.model.entity.Task;
import edu.cnm.deepdive.bard.model.pojo.TaskWithType;
import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends ArrayAdapter<TaskWithType> {

  private final LayoutInflater inflater;
  private final List<TaskWithType> currentTasks;

  public TaskAdapter(@NonNull Context context, List<TaskWithType> currentTasks) {
    super(context, R.layout.item_current_task, currentTasks);
    inflater = LayoutInflater.from(context);
    this.currentTasks = currentTasks;
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
    return binding.getRoot();
  }
}
