package jmc.com.poc_spotify.Data.API;

import jmc.com.poc_spotify.Entity.MasiveArtist;

import retrofit.Callback;
import retrofit.http.GET;

import retrofit.http.Query;

/**
 * Created by jogan1075 on 28-07-17.
 */

public interface SpotifyService {

    @GET("/search?type=artist")
    void searchArtists(@Query("q") String q, Callback<MasiveArtist> callback);

}
