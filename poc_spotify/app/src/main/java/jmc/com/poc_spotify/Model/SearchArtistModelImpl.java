package jmc.com.poc_spotify.Model;

import android.app.Activity;
import android.content.Context;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import jmc.com.poc_spotify.Application;
import jmc.com.poc_spotify.Data.CredentialsHandler;
import jmc.com.poc_spotify.Data.SpotifyCallback;
import jmc.com.poc_spotify.Data.SpotifyError;
import jmc.com.poc_spotify.Data.API.SpotifyRequest;

import jmc.com.poc_spotify.Data.API.SpotifyService;
import jmc.com.poc_spotify.Entity.MasiveArtist;
import retrofit.client.Response;


/**
 * Created by jogan1075 on 28-07-17.
 */

public class SearchArtistModelImpl implements SearchArtistModel {

    Context context;
    private static final String CLIENT_ID = "e573db3729ba460b9ebcb6ec02e7e849";
    private static final int REQUEST_CODE = 1337;
    private static final String REDIRECT_URI = "testschema://callback";
    SpotifyRequest spotifyApi;
    SpotifyService service;

    public SearchArtistModelImpl(Context context) {
        this.context = context;

         spotifyApi = new SpotifyRequest();

        String accessToken = CredentialsHandler.getToken(context);

        if (accessToken != null) {
            spotifyApi.setAccessToken(accessToken);
        } else {
//            logError("No valid access token");
        }


    }

    @Override
    public void LoginSpotify() {
        final AuthenticationRequest request = new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)
                .setScopes(new String[]{"playlist-read"})
                .build();

        AuthenticationClient.openLoginActivity((Activity) context, REQUEST_CODE, request);
    }

    @Override
    public void Search(String query, final CompleteListener callback) {

        service = spotifyApi.getService();

        service.searchArtists(query, new SpotifyCallback<MasiveArtist>() {
            @Override
            public void success(MasiveArtist artistsPager, Response response) {
                callback.onComplete(artistsPager.artists.items);
            }

            @Override
            public void failure(SpotifyError error) {
                callback.onError(error);
            }
        });
    }


}
