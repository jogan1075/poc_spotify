package jmc.com.poc_spotify.Model;

import java.util.List;

import jmc.com.poc_spotify.Entity.Artist;


/**
 * Created by jogan1075 on 28-07-17.
 */

public interface SearchArtistModel {

    void LoginSpotify();
    void Search(String query,CompleteListener callback);

    interface CompleteListener {
        void onComplete(List<Artist> items);
        void onError(Throwable error);
    }
}
