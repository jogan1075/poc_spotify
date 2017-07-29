package jmc.com.poc_spotify.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Map;


/**
 * Created by jogan1075 on 28-07-17.
 */

public class MyArtist implements Parcelable {
    public Map<String, String> external_urls;
    public String href;
    public String id;
    public String name;
    public String type;
    public String uri;


    public MyArtist() {
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeMap(this.external_urls);
        dest.writeString(this.href);
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeString(this.uri);
    }

    protected MyArtist(Parcel in) {
        this.external_urls = in.readHashMap(Map.class.getClassLoader());
        this.href = in.readString();
        this.id = in.readString();
        this.name = in.readString();
        this.type = in.readString();
        this.uri = in.readString();
    }

    public static final Creator<MyArtist> CREATOR = new Creator<MyArtist>() {
        public MyArtist createFromParcel(Parcel source) {
            return new MyArtist(source);
        }

        public MyArtist[] newArray(int size) {
            return new MyArtist[size];
        }
    };
}
