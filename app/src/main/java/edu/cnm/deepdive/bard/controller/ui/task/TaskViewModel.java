package edu.cnm.deepdive.bard.controller.ui.task;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TaskViewModel extends ViewModel {

  private MutableLiveData<String> mText;

  public TaskViewModel() {
    mText = new MutableLiveData<>();
    mText.setValue("This is the task fragment");
  }

  public LiveData<String> getText() {
    return mText;
  }
}