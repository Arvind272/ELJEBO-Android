package com.eljebo.customer.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.eljebo.R;
import com.eljebo.common.activity.BaseActivity;
import com.eljebo.common.adapter.ServiceAdapter;
import com.eljebo.common.data.SubService;
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.common.utils.CustomEditText;
import com.eljebo.common.utils.GoogleApisHandle;
import com.eljebo.customer.activity.CustomerMainActivity;
import com.eljebo.databinding.FragmentServiceProviderLocationBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;

/**
 * Created by TOXSL\vinay.goyal on 14/6/18.
 */

public class ServiceProviderLocationFragment extends BaseFragment implements BaseActivity.PermCallback, LocationListener {

    private View view;
    private FragmentServiceProviderLocationBinding binding;
    private GoogleMap googleMap;
    private LocationManager mLocationManager;
    private Location current_location;
    private int count = 0;
    private AlertDialog.Builder alert;
    private Calendar datetime;
    private ServiceAdapter serviceAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) parent.removeView(view);
        }
        try {

            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_service_provider_location, container, false);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.questionsLL.servicesRV.setLayoutManager(linearLayoutManager);

        if (baseActivity.store.containValue("selectedServices")) {
            serviceAdapter = new ServiceAdapter(this, baseActivity.store.<SubService>getData("selectedServices"));
            binding.questionsLL.servicesRV.setAdapter(serviceAdapter);
        } else {
            binding.questionsLL.cleanerLL.setVisibility(View.VISIBLE);
        }
        baseActivity.hideSoftKeyboard();
        binding.questionsLL.availabilityLL.setVisibility(View.VISIBLE);
        ((CustomerMainActivity) baseActivity).setTitle(getString(R.string.jack_thomas));
        if (baseActivity.checkPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 11, this)) {
            initUI();
        }
        binding.bookNowBT.setOnClickListener(this);
        binding.dateET.setOnClickListener(this);
        binding.timeET.setOnClickListener(this);
    }

    private void initUI() {
        initializeMap();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.bookNowBT:
                baseActivity.hideSoftKeyboard();
//                if (validate()) {
                gotoPayFragment();
//                }

                break;

            case R.id.dateET:
                showCalender(binding.dateET);
                break;

            case R.id.timeET:
                openClock(binding.timeET);
                break;
        }
    }

    private boolean validate() {
        if (binding.dateET.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.plz_slct_date));
        } else if (binding.timeET.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.plz_slct_time));
        } else if (binding.durationMinuteET.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.plz_slct_duration));
        } else {
            return true;
        }
        return false;
    }

    private void gotoPayFragment() {

        PayFragment payFragment = new PayFragment();
        Bundle bundle = new Bundle();
        bundle.putString("date", binding.dateET.getText().toString().trim());
        bundle.putString("time", binding.timeET.getText().toString().trim());
        bundle.putString("duration", binding.durationMinuteET.getText().toString().trim());
        payFragment.setArguments(bundle);
        baseActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.customer_container, payFragment)
                .addToBackStack(null)
                .commit();
    }

    private void initializeMap() {
        mLocationManager = (LocationManager) baseActivity.getSystemService(Context.LOCATION_SERVICE);
        if (googleMap == null) {
            SupportMapFragment map = ((SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map));
            map.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.getUiSettings().setZoomControlsEnabled(true);
                    googleMap.getUiSettings().setMapToolbarEnabled(false);
                    if (ActivityCompat.checkSelfPermission(baseActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    ServiceProviderLocationFragment.this.googleMap = googleMap;
                    current_location = GoogleApisHandle.getInstance(baseActivity).getLastKnownLocation(baseActivity);
                    initializeMap();
                }
            });
        } else {
            googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                @Override
                public void onMapLoaded() {
                    setMarker();
                }
            });
        }
    }


    private void setMarker() {
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

    @Override
    public void onResume() {
        super.onResume();
        if (current_location == null) {
            initializeMap();
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
            googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_loc_small)));

        }
    }

    private void openClock(CustomEditText customEditText) {
        datetime = Calendar.getInstance();
        int hour = datetime.get(Calendar.HOUR_OF_DAY);
        int minute = datetime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;

        mTimePicker = new TimePickerDialog(baseActivity, new timeListener(customEditText) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String am_pm = "";
                Calendar time = Calendar.getInstance();
                time.set(Calendar.HOUR_OF_DAY, selectedHour);
                time.set(Calendar.MINUTE, selectedMinute);
                customEditText.setText(baseActivity.changeDateFormatFromDate(time.getTime(), "HH:mm"));
            }
        }, hour, minute, true);
        mTimePicker.setTitle(getString(R.string.select_time));
        mTimePicker.show();
    }

    private void showCalender(CustomEditText customEditText) {
        datetime = Calendar.getInstance();
        int year = datetime.get(Calendar.YEAR);
        int month = datetime.get(Calendar.MONTH);
        int day = datetime.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(baseActivity, new dateListener(customEditText) {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int mon, int day) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, mon, day);
                customEditText.setText(baseActivity.changeDateFormatFromDate(calendar.getTime(), "MMM dd,yyyy"));
            }
        }, year, month, day);
        dialog.show();
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
    public void permGranted(int resultCode) {
        initUI();
    }

    @Override
    public void permDenied(int resultCode) {

    }

    abstract class timeListener implements TimePickerDialog.OnTimeSetListener {
        CustomEditText customEditText;

        timeListener(CustomEditText customEditText) {
            this.customEditText = customEditText;
        }
    }

    abstract class dateListener implements DatePickerDialog.OnDateSetListener {
        CustomEditText customEditText;

        dateListener(CustomEditText customEditText) {
            this.customEditText = customEditText;
        }
    }
}
