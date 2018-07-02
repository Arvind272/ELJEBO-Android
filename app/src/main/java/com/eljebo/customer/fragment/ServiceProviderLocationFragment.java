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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eljebo.R;
import com.eljebo.common.activity.BaseActivity;
import com.eljebo.common.adapter.ServiceAdapter;
import com.eljebo.common.data.SubService;
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.common.utils.Const;
import com.eljebo.common.utils.CustomEditText;
import com.eljebo.common.utils.GoogleApisHandle;
import com.eljebo.customer.activity.CustomerMainActivity;
import com.eljebo.customer.adapter.ServiceProvidersAdapter;
import com.eljebo.customer.cus_new_bean.ServiceProviderListBean;
import com.eljebo.databinding.FragmentServiceProviderLocationBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

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
    private String getServiceProviderUserId = "";

    String latitude = "";
    String longitude = "";

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

        getServiceProviderUserId = Const.loadData(getActivity(), "getServiceProviderUserId");

        if (baseActivity.store.containValue("selectedServices")) {
            serviceAdapter = new ServiceAdapter(this,
                    baseActivity.store.<SubService>getData("selectedServices"));
            binding.questionsLL.servicesRV.setAdapter(serviceAdapter);
        } else {
            binding.questionsLL.cleanerLL.setVisibility(View.VISIBLE);
        }
        baseActivity.hideSoftKeyboard();
        binding.questionsLL.availabilityLL.setVisibility(View.VISIBLE);
       // ((CustomerMainActivity) baseActivity).setTitle(getString(R.string.jack_thomas));
        getServiceProviderDetails();
        if (baseActivity.checkPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                11, this)) {
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
               if (validate()) {
                gotoPayFragment();
               }

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


    public void getServiceProviderDetails() {

        final ACProgressFlower acProgressFlower = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();
        acProgressFlower.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST,
                Const.NEW_BASE_URL
                + "getServiceProviderProfile",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            RequestQueue queue = Volley.newRequestQueue(getActivity());
                            queue.getCache().clear();
                            Log.e("getServiceProviderDetails==>>", response);
                            JSONObject json = new JSONObject(response);
                            String message = json.getString("message");

                            if (json.getString("status").equals("1")){

                                JSONObject jsonObjData = json.getJSONObject("data");

                                    String name = "";
                                    String gender = "";
                                    String address = "";

                                    String address2 = "";

                                    String zip_code = "";
                                    String start_time = "";
                                    String end_time = "";
                                    String country = "";
                                    String state = "";
                                    String city = "";
                                    String profile_pic = "";

                                    String fullAddressHere = "";

                                    if (!jsonObjData.isNull("name")){
                                        name = jsonObjData.getString("name");
                                        binding.questionsLL.customTextViewProName.setText(""+name);
                                        ((CustomerMainActivity) baseActivity).setTitle(""+name);
                                    }
                                    if (!jsonObjData.isNull("gender")){
                                        gender = jsonObjData.getString("gender");
                                    }
                                    if (!jsonObjData.isNull("address")){
                                        address = jsonObjData.getString("address");
                                        fullAddressHere = address;
                                    }

                                    if (!jsonObjData.isNull("address2")){
                                        address2 = jsonObjData.getString("address2");

                                        fullAddressHere = fullAddressHere +" " +address2;

                                    }
                                    if (!jsonObjData.isNull("latitude")){
                                        latitude = jsonObjData.getString("latitude");
                                    }
                                    if (!jsonObjData.isNull("longitude")){
                                        longitude = jsonObjData.getString("longitude");
                                    }
                                    if (!jsonObjData.isNull("zip_code")){
                                        zip_code = jsonObjData.getString("zip_code");
                                    }
                                    if (!jsonObjData.isNull("start_time")){
                                        start_time = jsonObjData.getString("start_time");
                                    }
                                    if (!jsonObjData.isNull("end_time")){
                                        end_time = jsonObjData.getString("end_time");
                                    }
                                    if (!jsonObjData.isNull("city")){
                                        city = jsonObjData.getString("city");
                                        fullAddressHere = fullAddressHere +" "+city;
                                    }
                                    if (!jsonObjData.isNull("state")){
                                        state = jsonObjData.getString("state");
                                        fullAddressHere = fullAddressHere +" "+state;
                                    }
                                    if (!jsonObjData.isNull("country")){
                                        country = jsonObjData.getString("country");
                                        fullAddressHere = fullAddressHere +" "+country;
                                    }

                                    binding.questionsLL.customTextViewAddress.setText(""+fullAddressHere);

                                    if (!jsonObjData.isNull("profile_pic")){
                                        profile_pic = jsonObjData.getString("profile_pic");

                                        Picasso.with(getActivity())
                                                .load(profile_pic)
                                                .placeholder(R.mipmap.ic_pic_home)
                                                .error(R.mipmap.ic_pic_home)
                                                .into(binding.questionsLL.mealImageOrder);
                                    }

                                initUI();

                            } else {
                            }

                            showToast(""+message);
                            if (acProgressFlower.isShowing()){
                                acProgressFlower.dismiss();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            if (acProgressFlower.isShowing()){
                                acProgressFlower.dismiss();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        if (acProgressFlower.isShowing()){
                            acProgressFlower.dismiss();
                        }
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                //user_id, token , service_provider_id

                Map<String, String> params = new HashMap<>();
                params.put("user_id", Const.loadData(getActivity(), "loginUserId"));
                params.put("token", Const.loadData(getActivity(), "loginUserToken"));
                params.put("service_provider_id", getServiceProviderUserId);

                Log.e("getServiceProviderDetai", "Params==>> " + params);

                return params;
            }
        };

        Volley.newRequestQueue(getActivity()).add(postRequest);
        Log.e("LOGIN", postRequest.toString());
        postRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
    }




    private void initializeMap() {
        mLocationManager = (LocationManager)
                baseActivity.getSystemService(Context.LOCATION_SERVICE);
        if (googleMap == null) {
            SupportMapFragment map = ((SupportMapFragment)
                    getChildFragmentManager().findFragmentById(R.id.map));
            map.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.getUiSettings().setZoomControlsEnabled(true);
                    googleMap.getUiSettings().setMapToolbarEnabled(false);
                    if (ActivityCompat.checkSelfPermission(baseActivity,
                            Manifest.permission.ACCESS_FINE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    ServiceProviderLocationFragment.this.googleMap = googleMap;
                    current_location = GoogleApisHandle.getInstance(baseActivity).
                            getLastKnownLocation(baseActivity);
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
                if (!latitude.equals("")){
                    setMarkeronMapDriverLoc(Double.parseDouble(latitude),
                            Double.parseDouble(longitude));
                }else {
                    setMarkeronMapDriverLoc(current_location.getLatitude(),
                            current_location.getLongitude());
                }

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
