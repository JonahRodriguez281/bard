package edu.cnm.deepdive.bard;

import android.app.Application;
import com.facebook.stetho.Stetho;
import edu.cnm.deepdive.bard.service.BardDatabase;
import edu.cnm.deepdive.bard.service.SpotifySignInService;
import io.reactivex.schedulers.Schedulers;

/**
 * Instantiates Bard as an Application that extends the Application class.
 */
public class BardApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Stetho.initializeWithDefaults(this);
    BardDatabase.setContext(this);
    BardDatabase.getInstance().getSongDao().delete()
        .subscribeOn(Schedulers.io())
        .subscribe();
    SpotifySignInService.setContext(this);
  }
}
