package jmc.com.poc_spotify.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jmc.com.poc_spotify.Model.SearchArtistModelImpl;
import jmc.com.poc_spotify.Presenter.SearchArtistPresenter;
import jmc.com.poc_spotify.R;
import jmc.com.poc_spotify.Utils.BusProvider;
import jmc.com.poc_spotify.View.SearchArtistView;


public class SearchArtistFragment extends android.app.Fragment {
    View view;
    SearchArtistPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search_artist, container, false);
        presenter = new SearchArtistPresenter(new SearchArtistModelImpl(getContext()), new SearchArtistView(this, view, getContext(),BusProvider.getInstance()));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.register(presenter);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.unregister(presenter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}