package com.example.destinationapps.models;

import java.util.ArrayList;
import java.util.List;

public class Author {
    private String name;
    private List<Places> places;

    public Author(String name) {
        this.name = name;
        this.places = new ArrayList<>();
    }

    public Author(String name, List<Places> places) {
        this.name = name;
        this.places = places;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Places> getPlaces() {
        return places;
    }

    public void addPlaces(Places places){
        this.places.add(places);
    }

    public void updatePlaces(int index, Places places){
        this.places.set(index, places);
    }

    public void deletePlaces(int index){
        this.places.remove(index);
    }
}
