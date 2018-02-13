package com.example.mohamed.weather_v32;

/**
 * Created by mohamed on 2/12/2018.
 */

public class coord {
    private String lon;
    private String lat;

    public coord(String lon, String lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
