package edu.cnm.deepdive.bard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.cnm.deepdive.bard.R;
import edu.cnm.deepdive.bard.databinding.ItemTaskBinding;
import edu.cnm.deepdive.bard.model.entity.Task;
import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Task> {

  private final LayoutInflater inflater;

  public TaskAdapter(@NonNull Context context, int resource) {
    super(context, R.layout.item_task, new ArrayList<Task>());
    inflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    ItemTaskBinding binding = (convertView != null)
        ? ItemTaskBinding.bind(convertView)
        : ItemTaskBinding.inflate(inflater, parent, false);
    Task task = getItem(position);
    binding.taskName.setText(task.getTaskName());
    binding.taskDescription.setText(task.getDescription());
    binding.taskDuration.setText(Integer.toString(task.getDuration()));
    return binding.getRoot();
  }
}
