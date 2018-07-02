package com.eljebo.common.service;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.location.Location;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.toxsl.volley.Request;
import com.toxsl.volley.VolleyError;
import com.toxsl.volley.toolbox.RequestParams;
import com.toxsl.volley.toolbox.SyncEventListner;
import com.toxsl.volley.toolbox.SyncManager;

import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;

import com.eljebo.BuildConfig;
import com.eljebo.R;
import com.eljebo.common.utils.Const;


public class LocationUpdateService extends Service implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, SyncEventListner, SensorEventListener {
    private static final String TAG = "LocationUpdateService";
    private static final long INTERVAL = 1000 * 30; // 30 seconds

    private SyncManager syncManager;
    private Context mContext;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private String mLastUpdateTime;
    private boolean notifyVisible;


    public LocationUpdateService(Context context) {
        this.mContext = context;
        syncManager = SyncManager.getInstance(context, BuildConfig.DEBUG);
        syncManager.setBaseUrl(Const.SERVER_REMOTE_URL, getString(R.string.app_name));
        init();
    }

    public LocationUpdateService() {

    }

    public static void startService(Context context) {
        Intent callIntent = new Intent(context, LocationUpdateService.class);
        context.startService(callIntent);
    }

    public static void stopService(Context context) {
        Intent callIntent = new Intent(context, LocationUpdateService.class);
        context.stopService(callIntent);
    }

    protected static void log(String string) {
        if (BuildConfig.DEBUG)
            Log.e(TAG, string);
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(mContext)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setSmallestDisplacement(10.0f);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mContext == null)
            mContext = this;
        syncManager = SyncManager.getInstance(this,BuildConfig.DEBUG);
        syncManager.setBaseUrl(Const.SERVER_REMOTE_URL, getString(R.string.app_name));
        init();
        notifyVisible = false;
        return super.onStartCommand(intent, flags, startId);
    }

    private void init() {
        createLocationRequest();
        buildGoogleApiClient();
        mGoogleApiClient.connect();
    }

    @Override
    public void onDestroy() {
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Intent restartServiceIntent = new Intent(getApplicationContext(), this.getClass());
        restartServiceIntent.setPackage(getPackageName());
        PendingIntent restartServicePendingIntent = PendingIntent.getService(getApplicationContext(), 1, restartServiceIntent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager alarmService = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        alarmService.set(
                AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + 1000,
                restartServicePendingIntent);
        super.onTaskRemoved(rootIntent);
    }

    @Override
    public void onConnected(Bundle bundle) {
        log("onConnected - isConnected ...............: " + mGoogleApiClient.isConnected());
        try {
            startLocationUpdates();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            generateNotification(1);
            stopService(mContext);
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
        log("Location update started ..............: ");
    }

    private boolean isGPSEnabled() {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        Boolean enable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (enable) {
            notifyVisible = false;
        }
        return enable;
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        log("Connection failed: " + connectionResult.toString());
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        log("Firing onLocationChanged................    latitude=  " + location.getLatitude() + "    longitude=   " + location.getLongitude() + "  " + mLastUpdateTime);
        handleNotifications();
        RequestParams params = new RequestParams();
        params.put("User[lat]", "" + location.getLatitude());
        params.put("User[long]", "" + location.getLongitude());
        syncManager.sendToServer("api/user/getLocation", params, this);
    }

    public void handleNotifications() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            generateNotification(1);
        } else if (!isGPSEnabled()) {
            generateNotification(2);
        }
    }

    private void generateNotification(int i) {
        if (!notifyVisible) {
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext)
//                    .setSmallIcon(R.mipmap.logo)
                    .setAutoCancel(true);
            Intent mainIntent;
            String message;
            if (i == 1) {
                mBuilder.setContentTitle(mContext.getString(R.string.app_name));
                message = mContext.getString(R.string.please_relaunch) + mContext.getString(R.string.app_name);
//                mainIntent = new Intent(mContext, SplashActivity.class);
//                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            } else {
                mBuilder.setContentTitle(mContext.getString(R.string.app_name) + mContext.getString(R.string.gps));
                message = mContext.getString(R.string.check_gps);
                mainIntent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            }
            mBuilder.setContentText(message);
            mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
            Uri NotiSound = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            mBuilder.setSound(NotiSound);
            long[] vibrate = {600, 100, 100, 700};
            mBuilder.setVibrate(vibrate);
//            PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 12, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) mContext
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            mNotificationManager.notify(i, mBuilder.build());
            notifyVisible = true;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSyncStart() {

    }

    @Override
    public void onSyncFinish() {

    }

    @Override
    public void onSyncFailure(VolleyError error, Request mRequest) {

    }

    @Override
    public void onSyncSuccess(String controller, String action, boolean status, JSONObject jsonObject) {
        log("Service onSyncSuccess--------" + action + "====" + status);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}
