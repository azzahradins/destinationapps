package com.example.destinationapps.models;

import java.util.List;

public class Author {
    private String name;
    private String date;
    private List<Places> places;

    public Author(String name, String date, List<Places> places) {
        this.name = name;
        this.date = date;
        this.places = places;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Places> getPlaces() {
        return places;
    }

    public void addPlaces(Places places){
        this.places.add(places);
    }
}
