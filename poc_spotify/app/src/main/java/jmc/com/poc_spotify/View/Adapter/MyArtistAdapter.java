package jmc.com.poc_spotify.View.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


import jmc.com.poc_spotify.Entity.Artist;
import jmc.com.poc_spotify.R;
import jmc.com.poc_spotify.View.SearchArtistView;


/**
 * Created by jogan1075 on 28-07-17.
 */

public class MyArtistAdapter extends RecyclerView.Adapter<MyArtistAdapter.ViewHolder> {

    private final List<Artist> mItems = new ArrayList<>();
    private final Context mContext;
    private final ItemSelectedListener mListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView title;
        public final TextView subtitle;
        public final ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.entity_title);
            subtitle = (TextView) itemView.findViewById(R.id.entity_subtitle);
            image = (ImageView) itemView.findViewById(R.id.entity_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            notifyItemChanged(getLayoutPosition());
//            mListener.onItemSelected(v, mItems.get(getAdapterPosition()));
        }
    }

    public interface ItemSelectedListener {
        void onItemSelected(View itemView, Artist item);
    }

    public MyArtistAdapter(Context context, ItemSelectedListener listener) {
        mContext = context;
        mListener = listener;
    }

    public void clearData() {
        mItems.clear();
    }

    public void addData(List<Artist> items) {
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_artista, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Artist item = mItems.get(position);
        holder.title.setText(item.name);
        if (item.images.size() > 0) {
            if (item.images.get(0) != null) {
                Picasso.with(mContext).load(item.images.get(0).url).into(holder.image);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (mItems.size() > 0) {
            return mItems.size() - mItems.size() + 1;
        } else {
            return mItems.size();
        }

    }
}
