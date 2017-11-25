package com.example.accepted.acceptedtalentplanet;

/**
 * Created by kwonhong on 2017-11-25.
 */

public class GeoPoint {
    double lat, lng;

    public GeoPoint(double lat, double lng){
        this.lat = lat;
        this.lng = lng;
    }

    public void setLat(double lat){
        this.lat = lat;
    }

    public double getLat(){
        return this.lat;
    }

    public void setLng(double lng){
        this.lng = lng;
    }

    public double getLng(){
        return this.lng;
    }

}
