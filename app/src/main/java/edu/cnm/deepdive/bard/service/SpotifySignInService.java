package edu.cnm.deepdive.bard.service;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import edu.cnm.deepdive.bard.controller.LoginResponseActivity;
import net.openid.appauth.AuthState;
import net.openid.appauth.AuthorizationException;
import net.openid.appauth.AuthorizationRequest;
import net.openid.appauth.AuthorizationResponse;
import net.openid.appauth.AuthorizationService;
import net.openid.appauth.AuthorizationServiceConfiguration;
import net.openid.appauth.ResponseTypeValues;

public class SpotifySignInService {

  private static Context context;
  private final AuthState authState;
  private final AuthorizationService service;

  private SpotifySignInService() {
    // TODO look for authState in shared preferences
    // TODO get authenticationUri & tokenUri from buildConfig
    AuthorizationServiceConfiguration serviceConfiguration =
        new AuthorizationServiceConfiguration(
            Uri.parse("https://accounts.spotify.com/authorize"),
            Uri.parse("https://accounts.spotify.com/api/token")
        );
    authState = new AuthState(serviceConfiguration);
    service = new AuthorizationService(context);

  }

  public static void setContext(Context context) {
    SpotifySignInService.context = context;
  }

  public static SpotifySignInService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  public void startSignIn(int requestCode, Class<? extends AppCompatActivity> completedActivity,
      Class<? extends AppCompatActivity> canceledActivity) {
    // TODO get clientId, callback from buildConfig
    //noinspection ConstantConditions
    AuthorizationRequest request = new AuthorizationRequest.Builder(
        authState.getAuthorizationServiceConfiguration(), "691050584001403cb5dee03b43d0bd4f",
        ResponseTypeValues.CODE, Uri.parse("bard.deepdive.cnm.edu://bard/oauth2callback")
        )
        .setScope("user-read-email user-library-read app-remote-control streaming playlist-modify-public playlist-modify-private playlist-read-private")
        .build();
    Intent completedIntent = new Intent(context, completedActivity)
        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    Intent canceledIntent = new Intent(context, canceledActivity)
        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    service.performAuthorizationRequest(request,
        PendingIntent.getActivity(context, requestCode, completedIntent, 0),
        PendingIntent.getActivity(context, requestCode, canceledIntent, 0)
        );
  }

  public void completeSignIn(AuthorizationResponse response, AuthorizationException ex,
      Intent successIntent, Intent failureIntent) {
    authState.update(response, ex);
    if (response != null) {
      service.performTokenRequest(
          response.createTokenExchangeRequest(),
          (tokenResponse, authEx) -> {
            authState.update(tokenResponse, authEx);
            if (tokenResponse != null) {
              context.startActivity(successIntent);
            } else {
              context.startActivity(failureIntent);
            }
          }
      );
    } else {
      context.startActivity(failureIntent);
    }
  }

  // TODO create refresh token method that returns a bearer token

  private static class InstanceHolder {

    @SuppressLint("StaticFieldLeak")
    private static final SpotifySignInService INSTANCE = new SpotifySignInService();

   }

}
