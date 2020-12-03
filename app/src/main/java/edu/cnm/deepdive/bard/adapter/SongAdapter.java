package edu.cnm.deepdive.bard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import edu.cnm.deepdive.bard.R;
import edu.cnm.deepdive.bard.databinding.ItemSongBinding;
import edu.cnm.deepdive.bard.model.entity.Song;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class SongAdapter extends ArrayAdapter<Song> {

  private final LayoutInflater inflater;

  public SongAdapter(Context context, List<Song> songList) {
    super(context, R.layout.item_song, songList);
    inflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    ItemSongBinding binding = (convertView != null)
        ? ItemSongBinding.bind(convertView)
        : ItemSongBinding.inflate(inflater, parent, false);
    Song song = getItem(position);
    binding.songName.setText(song.getSongName());
    binding.songArtist.setText(song.getArtist());
    binding.songAlbum.setText(song.getAlbum());
    return binding.getRoot();
  }

}
