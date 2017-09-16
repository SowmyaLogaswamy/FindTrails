package com.findtrails.findtrails.models;

/**
 * Created by Guest on 9/15/17.
 */

public class Trail {

    private String mCity;
    private String mState;
    private String mName;
    private String mDirections;


    public Trail(String city, String state, String name, String directions) {

        this.mCity = city;
        this.mState = state;
        this.mName = name;
        this.mDirections = directions;

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

}

