package com.eljebo.common.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Button;



import java.util.List;

public class AndroidGPSTrackingActivity implements LocationListener {

    Button btnShowLocation;
    private static final int PERMISSION_LOCATION_REQUEST_CODE = 100;
    public Double latitude;
    public Double longitude;
    MarshMallowPermission marshMallowPermission;

    public AndroidGPSTrackingActivity(Context context) {
        Activity a = (Activity)context;
        marshMallowPermission = new MarshMallowPermission(a);

        if (ContextCompat.checkSelfPermission(a, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(a, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSION_LOCATION_REQUEST_CODE);
        }
        LocationManager locationManager = (LocationManager)a. getSystemService(a.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        if(bestProvider!=null)
        {
            Location location = locationManager.getLastKnownLocation(bestProvider);
            if (location != null) {

                onLocationChanged(location);
            }
            locationManager.requestLocationUpdates(bestProvider, 20000, 0,AndroidGPSTrackingActivity.this);
            List<String> providers = locationManager.getProviders(true);
            Location l = null;
            for (int i = 0; i < providers.size(); i++) {
                l = locationManager.getLastKnownLocation(providers.get(i));
                if (l != null) {
                    latitude = l.getLatitude();
                    longitude = l.getLongitude();
                }
            }
        }

    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_LOCATION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // All good!
                } else {

                }

                break;
        }
    }

    @Override
    public void onLocationChanged(Location location) {

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



}