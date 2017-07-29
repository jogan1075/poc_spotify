package jmc.com.poc_spotify.Presenter;

import com.squareup.otto.Subscribe;

import java.util.List;

import jmc.com.poc_spotify.Entity.Artist;
import jmc.com.poc_spotify.Model.SearchArtistModel;
import jmc.com.poc_spotify.Model.SearchArtistModelImpl;
import jmc.com.poc_spotify.View.SearchArtistView;

/**
 * Created by jogan1075 on 28-07-17.
 */

public class SearchArtistPresenter implements SearchArtistModel.CompleteListener {

    private SearchArtistModelImpl model;
    private SearchArtistView view;

    public SearchArtistPresenter(SearchArtistModelImpl model, SearchArtistView view) {
        this.model = model;
        this.view = view;
    }


    @Subscribe
    public void LoginSpotify(SearchArtistView.Login event) {
        model.LoginSpotify();
    }

    @Subscribe
    public void SearchArtists(SearchArtistView.SearchArtist event) {
        view.showProgress();
        model.Search(event.nameArtist, this);
    }

    @Override
    public void onComplete(List<Artist> items) {
        view.addDataList(items);
        view.hideProgress();
    }

    @Override
    public void onError(Throwable error) {

    }
}
