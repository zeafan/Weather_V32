package com.example.mohamed.weather_v32;

import java.util.List;

/**
 * Created by mohamed on 2/12/2018.
 */

public class OpenWeatherMap {
    private clouds clouds;
    private coord coord;
    private main main;
    private sys sys;
    private List<weather> weather;
    private wind wind;
    private String base;
    private int dt;
    private int id;
    private  String name;
   private int code;

    public OpenWeatherMap(clouds clouds, coord coord, main main, com.example.mohamed.weather_v32.sys sys,
                          List<weather> weather, com.example.mohamed.weather_v32.wind wind, String base,
                          int dt, int id, String name, int code) {
        this.clouds = clouds;
        this.coord = coord;
        this.main = main;
        this.sys = sys;
        this.weather = weather;
        this.wind = wind;
        this.base = base;
        this.dt = dt;
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public OpenWeatherMap() {
    }

    public clouds getClouds() {
        return clouds;
    }

    public void setClouds(clouds clouds) {
        this.clouds = clouds;
    }

    public com.example.mohamed.weather_v32.coord getCoord() {
        return coord;
    }

    public void setCoord(com.example.mohamed.weather_v32.coord coord) {
        this.coord = coord;
    }

    public com.example.mohamed.weather_v32.main getMain() {
        return main;
    }

    public void setMain(com.example.mohamed.weather_v32.main main) {
        this.main = main;
    }

    public com.example.mohamed.weather_v32.sys getSys() {
        return sys;
    }

    public void setSys(com.example.mohamed.weather_v32.sys sys) {
        this.sys = sys;
    }

    public List<weather> getWeather() {
        return weather;
    }

    public void setWeather(List<weather> weather) {
        this.weather = weather;
    }

    public com.example.mohamed.weather_v32.wind getWind() {
        return wind;
    }

    public void setWind(com.example.mohamed.weather_v32.wind wind) {
        this.wind = wind;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
