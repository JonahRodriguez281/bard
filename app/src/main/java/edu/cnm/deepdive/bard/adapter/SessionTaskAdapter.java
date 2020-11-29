package edu.cnm.deepdive.bard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.bard.adapter.SessionTaskAdapter.Holder;
import edu.cnm.deepdive.bard.databinding.ItemTaskBinding;
import edu.cnm.deepdive.bard.model.entity.Task;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class SessionTaskAdapter extends RecyclerView.Adapter<Holder> {

  private final Context context;
  private final List<Task> tasks;
  private final LayoutInflater inflater;

  public SessionTaskAdapter(@NonNull Context context) {
    this.context = context;
    tasks = new ArrayList<>();
    inflater = LayoutInflater.from(context);
  }

  public List<Task> getTasks() {
    return tasks;
  }

  @NotNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemTaskBinding binding = ItemTaskBinding.inflate(inflater, parent, false);
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull @NotNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return tasks.size();
  }

  class Holder extends RecyclerView.ViewHolder {

    private final ItemTaskBinding binding;

    private Holder(@NonNull ItemTaskBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    private void bind(int position) {
      Task task = tasks.get(position);
      binding.taskName.setText(task.getTaskName());
      binding.taskDescription.setText(task.getDescription());
      binding.taskDuration.setText(task.getDuration());
    }
  }


}
