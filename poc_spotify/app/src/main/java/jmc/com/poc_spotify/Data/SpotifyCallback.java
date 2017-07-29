package jmc.com.poc_spotify.Data;


import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by jogan1075 on 28-07-17.
 */

public abstract class SpotifyCallback<T> implements Callback<T> {
    public abstract void failure(SpotifyError error);

    @Override
    public void failure(RetrofitError error) {
        failure(SpotifyError.fromRetrofitError(error));
    }
}