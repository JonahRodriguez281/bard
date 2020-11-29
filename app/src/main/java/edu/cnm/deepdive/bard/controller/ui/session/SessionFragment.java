package edu.cnm.deepdive.bard.controller.ui.session;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.bard.adapter.SessionTaskAdapter;
import edu.cnm.deepdive.bard.databinding.FragmentSessionBinding;

public class SessionFragment extends Fragment {

  private SessionViewModel sessionViewModel;
  private FragmentSessionBinding binding;
  private SessionTaskAdapter adapter;

  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentSessionBinding.inflate(getLayoutInflater());
    return binding.getRoot();
  }

  public void setupViewModel() {
    FragmentActivity activity = getActivity();
    //noinspection ConstantConditions
    adapter = new SessionTaskAdapter(activity);
    binding.currentTaskList.setAdapter(adapter);
    sessionViewModel = new ViewModelProvider(activity).get(SessionViewModel.class);
    getLifecycle().addObserver(sessionViewModel);
  }
 }