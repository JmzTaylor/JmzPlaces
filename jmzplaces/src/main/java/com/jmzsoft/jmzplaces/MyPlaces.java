package com.jmzsoft.jmzplaces;

public class MyPlaces {

    private String mName;
    private String mAddress;
    private String mRating;

    MyPlaces(String _mName, String _mAddress, String _mRating) {
        this.mName = _mName;
        this.mAddress = _mAddress;
        this.mRating = _mRating;
    }

    public String getName() {
        return mName;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getRating() {
        return mRating;
    }
}
