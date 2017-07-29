package jmc.com.poc_spotify.Data.API;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.MainThreadExecutor;

/**
 * Created by jogan1075 on 28-07-17.
 */

public class SpotifyRequest {
    public static final String SPOTIFY_WEB_API_ENDPOINT = "https://api.spotify.com/v1";


    private class WebApiAuthenticator implements RequestInterceptor {
        @Override
        public void intercept(RequestFacade request) {
            if (mAccessToken != null) {
                request.addHeader("Authorization", "Bearer " + mAccessToken);
            }
        }
    }

    private final SpotifyService mSpotifyService;

    private String mAccessToken;


    public SpotifyRequest(Executor httpExecutor, Executor callbackExecutor) {
        mSpotifyService = init(httpExecutor, callbackExecutor);
    }

    private SpotifyService init(Executor httpExecutor, Executor callbackExecutor) {

        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.BASIC)
                .setExecutors(httpExecutor, callbackExecutor)
                .setEndpoint(SPOTIFY_WEB_API_ENDPOINT)
                .setRequestInterceptor(new SpotifyRequest.WebApiAuthenticator())
                .build();

        return restAdapter.create(SpotifyService.class);
    }

    public SpotifyRequest() {
        Executor httpExecutor = Executors.newSingleThreadExecutor();
        MainThreadExecutor callbackExecutor = new MainThreadExecutor();
        mSpotifyService = init(httpExecutor, callbackExecutor);
    }


    public SpotifyRequest setAccessToken(String accessToken) {
        mAccessToken = accessToken;
        return this;
    }

    public SpotifyService getService() {
        return mSpotifyService;
    }
}