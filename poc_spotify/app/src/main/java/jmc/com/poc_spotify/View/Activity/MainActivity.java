package jmc.com.poc_spotify.View.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import java.util.concurrent.TimeUnit;

import jmc.com.poc_spotify.Data.CredentialsHandler;
import jmc.com.poc_spotify.R;
import jmc.com.poc_spotify.View.Fragment.SearchArtistFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        jmc.com.poc_spotify.Application.setActivity(this);

        getFragmentManager().beginTransaction()
                .replace(R.id.artist_fragment, new SearchArtistFragment()).commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, intent);
        switch (response.getType()) {
            case TOKEN:
                Log.e("SPOTIFY", "Got token: " + response.getAccessToken());
                CredentialsHandler.setToken(this, response.getAccessToken(), response.getExpiresIn(), TimeUnit.SECONDS);
//               /**/ jmc.com.poc_spotify.Application.setToken(response.getAccessToken());
                getFragmentManager().beginTransaction()
                        .replace(R.id.artist_fragment, new SearchArtistFragment()).commit();
                break;
            case ERROR:
                Log.e("SPOTIFY", "Auth error: " + response.getError());
                break;
            default:
                Log.e("SPOTIFY", "Auth result: " + response.getType());
        }
    }
}
