package edu.cnm.deepdive.bard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import edu.cnm.deepdive.bard.model.entity.Song;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class SongAdapter extends RecyclerView.Adapter {

  private final Context context;
  private final List<Song> songs;
  private final LayoutInflater inflater;

  public SongAdapter(Context context) {
    this.context = context;
    inflater = LayoutInflater.from(context);
    songs = new ArrayList<>();
  }

  public List<Song> getSongs() {
    return songs;
  }

  @NotNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return null;
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }

  class Holder extends RecyclerView.ViewHolder {

    public Holder(@NonNull View itemView) {
      super(itemView);
    }
  }
}
