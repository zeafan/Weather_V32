package com.example.mohamed.weather_v32;

import android.annotation.SuppressLint;

import java.security.PublicKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mohamed on 2/12/2018.
 */

public class Common {
    public static String API_KEY = "c531f8f6882b6e0e9ae8b24cbe444d05";
    public static String API_LINK = "http://api.openweathermap.org/data/2.5/weather";

   static String APIRequest(String Lat, String Lon) {
        StringBuilder sp = new StringBuilder(API_LINK);
        sp.append(String.format("?lat=%s&lon=%s&APPID=%s&units_metric", Lat, Lon, API_KEY));
        return sp.toString();
    }

    public static String unixTimeStampDateTimp(double unixTimeStamp) {
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        date.setTime((long) unixTimeStamp * 1000);
        return dateFormat.format(date);
    }

    public static String getImage(String icon) {
        return String.format("http://api.openweathermap.org/img/w/%s.png", icon);
    }

    public static String getDateNow() {
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
