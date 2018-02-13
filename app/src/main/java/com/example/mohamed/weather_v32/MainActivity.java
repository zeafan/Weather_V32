package com.example.mohamed.weather_v32;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Type;

public class MainActivity extends AppCompatActivity implements LocationListener {
    TextView txtCity, txtLastUpdate, txtDescrip, txtHumidity, txtTime, txtCelisius;
    ImageView imageView;
    LocationManager locationManager;
    String Provider;
    static double lat, log;
    OpenWeatherMap openWeatherMap = new OpenWeatherMap();
    final private int My_Perimession = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         txtCity = findViewById(R.id.txtCity);
        txtDescrip = findViewById(R.id.txtDescription);
        txtCelisius = findViewById(R.id.txtCelsuis);
        txtHumidity = findViewById(R.id.txtHumidy);
         txtLastUpdate = findViewById(R.id.txtLastUpdate);
         txtTime = findViewById(R.id.txtTime);
         imageView = findViewById(R.id.icon);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Provider = locationManager.getBestProvider(new Criteria(), false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.INTERNET,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,

            }, My_Perimession);
        }
        locationManager.getLastKnownLocation(Provider);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(this);
    }

    @Override
    protected void onResume() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.INTERNET,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.SYSTEM_ALERT_WINDOW,

            }, My_Perimession);
        }
        locationManager.requestLocationUpdates(Provider, 400, 1, this);
        super.onResume();
    }

    @Override
    public void onLocationChanged(Location location) {
        double lon=location.getLongitude();
        double lit=location.getLatitude();
        String API_req=Common.APIRequest(String.valueOf(lit),String.valueOf(lon));
        new GetWeather().execute(API_req);

    }
    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
    class GetWeather extends AsyncTask<String,Void,String>
    {
        ProgressDialog progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
             progressDialog=new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Please Wait...");
            progressDialog.show();}



        @Override
        protected String doInBackground(String... strings) {
            String Url=strings[0];
            Helper helper=new Helper();
            try {
                return helper.getHttpDate(Url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        @SuppressLint("DefaultLocale")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson=new Gson();
            Type type=new  TypeToken<OpenWeatherMap>(){}.getType();
            openWeatherMap=gson.fromJson(s,type);
            progressDialog.dismiss();
            txtCity.setText(String.format("%s,%s",openWeatherMap.getName(),openWeatherMap.getSys().country));
            txtCelisius.setText(String.format("%.2f  C",openWeatherMap.getMain().getTemp()));
            txtDescrip.setText(String.format("Last Update:  %s",openWeatherMap.getWeather().get(0).getDescription()));
            txtHumidity.setText(String.format("Last Update:  %s",openWeatherMap.getMain().getHumidity()));
            txtLastUpdate.setText(String.format("Last Update:  %s",Common.getDateNow()));
            txtTime.setText(String.format("%s/%s",Common.unixTimeStampDateTimp(openWeatherMap.getSys().getSunrise()),Common.unixTimeStampDateTimp(openWeatherMap.getSys().getSunset())));
            Picasso.with(MainActivity.this).load(Common.getImage(openWeatherMap.getWeather().get(0).getIcon())).into(imageView);
        }
    }
}
