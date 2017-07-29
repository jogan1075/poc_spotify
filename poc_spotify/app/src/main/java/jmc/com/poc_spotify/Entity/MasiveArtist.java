package jmc.com.poc_spotify.Entity;

import android.os.Parcel;
import android.os.Parcelable;



/**
 * Created by jogan1075 on 28-07-17.
 */

public class MasiveArtist implements Parcelable {
    public Pager<Artist> artists;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.artists, 0);
    }

    public MasiveArtist() {
    }

    protected MasiveArtist(Parcel in) {
        this.artists = in.readParcelable(Pager.class.getClassLoader());
    }

    public static final Parcelable.Creator<MasiveArtist> CREATOR = new Parcelable.Creator<MasiveArtist>() {
        public MasiveArtist createFromParcel(Parcel source) {
            return new MasiveArtist(source);
        }

        public MasiveArtist[] newArray(int size) {
            return new MasiveArtist[size];
        }
    };
}