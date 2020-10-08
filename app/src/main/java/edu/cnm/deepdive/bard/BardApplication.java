package edu.cnm.deepdive.bard;

import android.app.Application;
import com.facebook.stetho.Stetho;

public class BardApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
  }
}
