package com.findtrails.findtrails.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Trail {
    private String mCity;
    private String mState;
    private String mName;
    private String mDirections;
    private String mDescription;
    private String mUrl;

    public Trail() {}

    public Trail(String city, String state, String name, String directions, String description, String url) {
        this.mCity = city;
        this.mState = state;
        this.mName = name;
        this.mDirections = directions;
        this.mDescription = description;
        this.mUrl = url;
    }

    public String getCity() {
        return mCity;
    }

    public String getState() {
        return mState;
    }

    public String getName() {
        return mName;
    }

    public String getDirections() {
        return mDirections;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getUrl() {
        return mUrl;
    }
}

