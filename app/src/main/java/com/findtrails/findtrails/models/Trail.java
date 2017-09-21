package com.findtrails.findtrails.models;

public class Trail {
    private String mCity;
    private String mState;
    private String mName;
    private String mDirections;
    private String mDescription;
    public Trail(String city, String state, String name, String directions, String description) {
        this.mCity = city;
        this.mState = state;
        this.mName = name;
        this.mDirections = directions;
        this.mDescription = description;
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
}

