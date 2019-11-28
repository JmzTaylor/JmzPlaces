package com.jmzsoft.jmzplaces;

class MyLocation {

    private double mLatitude;
    private double mLongitude;

    MyLocation(double _mLatitude, double _mLongitude) {
        this.mLatitude = _mLatitude;
        this.mLongitude = _mLongitude;
    }

    double getLatitude() {
        return mLatitude;
    }

    double getLongitude() {
        return mLongitude;
    }
}
