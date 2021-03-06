package com.eljebo.serviceprovider.fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eljebo.R;
import com.eljebo.common.activity.BaseActivity;
import com.eljebo.common.adapter.ServiceAdapter;
import com.eljebo.common.data.SubService;
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.common.utils.GoogleApisHandle;
import com.eljebo.databinding.FragmentHomeBinding;
import com.eljebo.databinding.LayoutInfoWindowBinding;
import com.eljebo.serviceprovider.activity.MainActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by TOXSL\himanshu.gulati on 12/6/18.
 */

public class HomeFragment extends BaseFragment implements
        BaseActivity.PermCallback, LocationListener, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {
    private View view;
    private FragmentHomeBinding binding;
    private GoogleMap googleMap;
    private LocationManager mLocationManager;
    private Location current_location;
    private int count = 0;
    private AlertDialog.Builder alert;
    private LayoutInfoWindowBinding infoWindowBinding;
    private ServiceAdapter serviceAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) parent.removeView(view);
        }
        try {

            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) getActivity()).setTitle(getString(R.string.home));
        this.view = view;
        baseActivity.hideSoftKeyboard();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.questionsLL.servicesRV.setLayoutManager(linearLayoutManager);

        if (baseActivity.store.containValue("selectedServices")) {
            serviceAdapter = new ServiceAdapter(this, baseActivity.store.<SubService>getData("selectedServices"));
            binding.questionsLL.servicesRV.setAdapter(serviceAdapter);
        } else {
            binding.questionsLL.cleanerLL.setVisibility(View.VISIBLE);
        }

        if (baseActivity.checkPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 11, this)) {
            init();
        }
        binding.userDetailNSV.setVisibility(View.GONE);
        binding.acceptBT.setOnClickListener(this);
        binding.rejectBT.setOnClickListener(this);
    }

    private void init() {
        initializeMap();
    }

    @Override
    public void permGranted(int resultCode) {
        init();
    }

    @Override
    public void permDenied(int resultCode) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.acceptBT:
                baseActivity.hideSoftKeyboard();
                loadServiceUserDetailFragment();
                break;
            case R.id.rejectBT:
                baseActivity.hideSoftKeyboard();
                binding.userDetailNSV.setVisibility(View.GONE);
                break;

        }
    }

    private void loadServiceUserDetailFragment() {
        baseActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ServiceUserDetailFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (current_location == null) {
            initializeMap();
        }
    }

    private void initializeMap() {
        mLocationManager = (LocationManager) baseActivity.
                getSystemService(Context.LOCATION_SERVICE);
        if (googleMap == null) {
            SupportMapFragment map = ((SupportMapFragment)
                    getChildFragmentManager().findFragmentById(R.id.map));
            map.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.getUiSettings().setZoomControlsEnabled(true);
                    googleMap.getUiSettings().setMapToolbarEnabled(false);
                    if (ActivityCompat.checkSelfPermission(baseActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    HomeFragment.this.googleMap = googleMap;
                    current_location = GoogleApisHandle.getInstance(baseActivity).
                            getLastKnownLocation(baseActivity);

                    initializeMap();
                }
            });
        } else {
            googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                @Override
                public void onMapLoaded() {

                    googleMap.setOnMarkerClickListener(HomeFragment.this);
                    googleMap.setOnInfoWindowClickListener(HomeFragment.this);
                    setMarker();
                }
            });
        }
    }


    private void setMarker() {
        LocationManager mLocationManager = (LocationManager) baseActivity.getSystemService(Context.LOCATION_SERVICE);

        if (current_location != null) {
            if (googleMap != null)
                setMarkeronMapDriverLoc(current_location.getLatitude(), current_location.getLongitude());
        } else {
            if (mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                if (count < 5) {
                    count++;
                    current_location = GoogleApisHandle.getInstance(baseActivity).getLastKnownLocation(baseActivity);
                    onLocationChanged(current_location);
                } else
                    baseActivity.buildAlertMessageNoGps();
            } else
                baseActivity.buildAlertMessageNoGps();
        }

    }


    public void setMarkeronMapDriverLoc(double latitude, double longitude) {
        if (googleMap != null) {

            googleMap.clear();
            googleMap.addCircle(new CircleOptions()
                    .center(new LatLng(latitude, longitude))
                    .radius(500)
                    .strokeColor(Color.TRANSPARENT)
                    .fillColor(ContextCompat.getColor(baseActivity, R.color.map_color)));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 14));
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitude, longitude)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_loc_small)));
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        current_location = location;
        setMarker();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        current_location = new GoogleApisHandle().getLastKnownLocation(baseActivity);
        onLocationChanged(current_location);

    }

    @Override
    public void onProviderDisabled(String provider) {
        if (provider.equalsIgnoreCase(LocationManager.GPS_PROVIDER))
            buildAlertMessageNoGps();

    }


    private void buildAlertMessageNoGps() {
        if (isAdded()) {
            if (alert == null) {
                alert = new AlertDialog.Builder(baseActivity);
                alert.setMessage(getString(R.string.your_gps_seems_to_be_disable))
                        .setCancelable(false)
                        .setPositiveButton(baseActivity.getString(R.string.yes), new DialogInterface.OnClickListener() {
                            public void onClick(@SuppressWarnings("unused") DialogInterface dialog, @SuppressWarnings("unused") int id) {
                                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                alert = null;
                            }
                        })
                        .setNegativeButton(baseActivity.getString(R.string.no), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, @SuppressWarnings("unused") int id) {
                                dialog.cancel();
                                alert = null;
                            }
                        });

                alert.show();
            }
        }

    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                infoWindowBinding = DataBindingUtil.
                        inflate(LayoutInflater.from(baseActivity),
                                R.layout.layout_info_window,
                                null, false);
                return infoWindowBinding.getRoot();
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });

        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        binding.userDetailNSV.setVisibility(View.VISIBLE);

    }
}
