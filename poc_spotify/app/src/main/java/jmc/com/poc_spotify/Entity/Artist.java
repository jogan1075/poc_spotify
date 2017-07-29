package jmc.com.poc_spotify.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;



/**
 * Created by jogan1075 on 28-07-17.
 */

public class Artist extends MyArtist {
    public List<String> genres;
    public List<Image> images;
    public Integer popularity;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeStringList(this.genres);
        dest.writeTypedList(images);
        dest.writeValue(this.popularity);
    }

    public Artist() {
    }

    protected Artist(Parcel in) {
        super(in);
        this.genres = in.createStringArrayList();
        this.images = in.createTypedArrayList(Image.CREATOR);
        this.popularity = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<Artist> CREATOR = new Parcelable.Creator<Artist>() {
        public Artist createFromParcel(Parcel source) {
            return new Artist(source);
        }

        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };
}