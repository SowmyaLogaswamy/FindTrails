package com.findtrails.findtrails.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Trail {
    private String city;
    private String state;
    private String name;
    private String directions;
    private String description;
    private String url;

    public Trail() {}

    public Trail(String city, String state, String name, String directions, String description, String url) {
        this.city = city;
        this.state = state;
        this.name = name;
        this.directions = directions;
        this.description = description;
        this.url = url;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public String getDirections() {
        return directions;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }
}

