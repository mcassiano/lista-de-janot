package me.cassiano.listadejanot.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by matheus on 3/20/15.
 */
public class Politician implements Parcelable {

    private int id;
    private String name;
    private String party;
    private String position;
    private String state;
    private String status;
    private String picture;
    private String suspicion;
    private String defense;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSuspicion() {
        return suspicion;
    }

    public void setSuspicion(String suspicion) {
        this.suspicion = suspicion;
    }

    public String getDefense() {
        return defense;
    }

    public void setDefense(String defense) {
        this.defense = defense;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.party);
        dest.writeString(this.position);
        dest.writeString(this.state);
        dest.writeString(this.status);
        dest.writeString(this.picture);
        dest.writeString(this.suspicion);
        dest.writeString(this.defense);
    }

    public Politician(Parcel pc) {
        this.id = pc.readInt();
        this.name = pc.readString();
        this.party = pc.readString();
        this.position = pc.readString();
        this.state = pc.readString();
        this.status = pc.readString();
        this.picture = pc.readString();
        this.suspicion = pc.readString();
        this.defense = pc.readString();
    }

    public static final Parcelable.Creator<Politician> CREATOR = new Parcelable.Creator<Politician>() {
        public Politician createFromParcel(Parcel in) {
            return new Politician(in);
        }

        public Politician[] newArray(int size) {
            return new Politician[size];
        }
    };
}
