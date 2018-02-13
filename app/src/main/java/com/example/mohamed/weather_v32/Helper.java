package com.example.mohamed.weather_v32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mohamed on 2/12/2018.
 */

public class Helper {
    static String stream=null;
    public Helper() {
    }
    public String getHttpDate(String urlString) throws IOException {
        URL url=new URL(urlString);
        HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
        if(httpURLConnection.getResponseCode()==200)
        {
            BufferedReader Bf=new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuilder sb=new StringBuilder();
            String line="";
            while ((line=Bf.readLine())!=null)
                sb.append(line);
            stream=sb.toString();
            httpURLConnection.disconnect();
        }
        return stream;
    }
}
