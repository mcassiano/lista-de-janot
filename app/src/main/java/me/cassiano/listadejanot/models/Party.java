package me.cassiano.listadejanot.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by matheus on 3/20/15.
 */
public class Party implements Parcelable {

    private String picture;
    private List<Politician> politicians;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<Politician> getPoliticians() {
        return politicians;
    }

    public void setPoliticians(List<Politician> politicians) {
        this.politicians = politicians;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(this.picture);
        dest.writeTypedList(this.politicians);
        dest.writeString(this.name);
    }

    public Party(Parcel pc) {
        this.picture = pc.readString();
        pc.readTypedList(this.politicians, Politician.CREATOR);
        this.name = pc.readString();
    }

    public static final Parcelable.Creator<Party> CREATOR = new Parcelable.Creator<Party>() {
        public Party createFromParcel(Parcel in) {
            return new Party(in);
        }

        public Party[] newArray(int size) {
            return new Party[size];
        }
    };

}
