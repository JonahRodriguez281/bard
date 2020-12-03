package edu.cnm.deepdive.bard.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.cnm.deepdive.bard.BuildConfig;
import edu.cnm.deepdive.bard.model.dto.TracksResponse;
import edu.cnm.deepdive.bard.model.entity.User;
import io.reactivex.Completable;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Service Proxy for Incorporating Spotify Service and Endpoints
 */
public interface SpotifyServiceProxy {

  @GET("me")
  Single<User> getProfile(@Header("Authorization") String bearerToken);

  // TODO Finish Writing all Endpoints for service
  @GET("me/tracks")
  Single<TracksResponse> getTracks(@Header("Authorization") String bearerToken);

  @POST("me/player/queue")
  Completable addToQueue(@Header("Authorization") String bearerToken,
      @Query("uri") String trackUri);

  static SpotifyServiceProxy getInstance() {
    return InstanceHolder.INSTANCE;
  }

  class InstanceHolder {

    private static final SpotifyServiceProxy INSTANCE;

    static {
      Gson gson = new GsonBuilder()
          .excludeFieldsWithoutExposeAnnotation()
          .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
          .create();
      HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
      interceptor.setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE);
      OkHttpClient client = new OkHttpClient.Builder()
          .addInterceptor(interceptor)
          .build();
      Retrofit retrofit = new Retrofit.Builder()
          .addConverterFactory(GsonConverterFactory.create(gson))
          .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
          .client(client)
          .baseUrl(BuildConfig.BASE_URL)
          .build();
      INSTANCE = retrofit.create(SpotifyServiceProxy.class);

    }
  }
}
