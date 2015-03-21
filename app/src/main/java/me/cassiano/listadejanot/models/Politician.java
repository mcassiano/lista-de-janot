package me.cassiano.listadejanot.models;

/**
 * Created by matheus on 3/20/15.
 */
public class Politician {

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
}
