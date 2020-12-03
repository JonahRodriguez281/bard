package edu.cnm.deepdive.bard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.cnm.deepdive.bard.R;
import edu.cnm.deepdive.bard.databinding.ItemSongBinding;
import edu.cnm.deepdive.bard.model.entity.Song;
import java.util.List;

/**
 * Adapter for correctly displaying {@link Song}s in the {@link edu.cnm.deepdive.bard.controller.ui.music.MusicFragment}.
 */
public class SongAdapter extends ArrayAdapter<Song> {

  private final LayoutInflater inflater;

  /**
   * Constructor for the TaskAdapter
   *
   * @param context Relevant context for the constructor
   * @param songs   List of songs to be adapted
   */
  public SongAdapter(Context context, List<Song> songs) {
    super(context, R.layout.item_song, songs);

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
    binding.songArtist.setText(song.getArtists().toString());
    binding.songAlbum.setText(song.getAlbumDto().getName());
    return binding.getRoot();
  }

}
