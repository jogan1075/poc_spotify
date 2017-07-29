package jmc.com.poc_spotify.View;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;

import com.squareup.otto.Bus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jmc.com.poc_spotify.Data.CredentialsHandler;
import jmc.com.poc_spotify.Entity.Artist;
import jmc.com.poc_spotify.R;
import jmc.com.poc_spotify.Utils.FragmentView;
import jmc.com.poc_spotify.View.Adapter.MyArtistAdapter;


/**
 * Created by jogan1075 on 28-07-17.
 */

public class SearchArtistView extends FragmentView {

    private Bus bus;
    @BindView(R.id.search_input)
    EditText search_input;

    @BindView(R.id.artistListView)
    RecyclerView lista;

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.button2)
    Button button2;

    MyArtistAdapter adapter;
    private LinearLayoutManager mLayoutManager;
    ProgressDialog progressDialog;

    public SearchArtistView(Fragment fragment, View view, Context context, Bus bus) {
        super(fragment, view, context);
        this.bus = bus;
        ButterKnife.setDebug(true);
        ButterKnife.bind(this, view);

        String token = CredentialsHandler.getToken(context);
        if (token != null) {
            showandhideElements();
        }

        adapter = new MyArtistAdapter(getContext(), new MyArtistAdapter.ItemSelectedListener() {
            @Override
            public void onItemSelected(View itemView, Artist item) {

            }
        });

        mLayoutManager= new LinearLayoutManager(context);
        lista.setLayoutManager(mLayoutManager);
        lista.setHasFixedSize(true);
        lista.setAdapter(adapter);

        progressDialog = new ProgressDialog(getContext());
    }


    public void showandhideElements() {
        button.setVisibility(View.GONE);
        button2.setVisibility(View.VISIBLE);
        search_input.setVisibility(View.VISIBLE);
        lista.setVisibility(View.VISIBLE);

    }

    public void reset() {
        adapter.clearData();
    }

    public void addDataList(List<Artist> items) {
        adapter.addData(items);
    }

    public void showProgress() {
        progressDialog.setMessage("Cargando....");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public void hideProgress() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @OnClick(R.id.button)
    public void ClickLogin() {
        bus.post(new Login());
    }

    @OnClick(R.id.button2)
    public void SearchArtist() {
        bus.post(new SearchArtist(search_input.getText().toString()));
    }

    public class Login {
    }

    public class SearchArtist {
        public SearchArtist(String nameArtist) {
            this.nameArtist = nameArtist;
        }

        public String nameArtist;
    }
}
