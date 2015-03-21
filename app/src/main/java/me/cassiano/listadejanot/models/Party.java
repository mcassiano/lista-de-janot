package me.cassiano.listadejanot.models;

import java.util.Comparator;
import java.util.List;

/**
 * Created by matheus on 3/20/15.
 */
public class Party {

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

}
