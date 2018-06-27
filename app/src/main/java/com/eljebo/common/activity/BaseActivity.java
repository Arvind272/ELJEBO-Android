package com.eljebo.common.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.eljebo.BuildConfig;
import com.eljebo.R;
import com.eljebo.common.data.ProfileData;
import com.eljebo.common.fragment.OtpFragment;
import com.eljebo.common.snackBAr.ActionClickListener;
import com.eljebo.common.snackBAr.Snackbar;
import com.eljebo.common.snackBAr.SnackbarManager;
import com.eljebo.common.snackBAr.SnackbarType;
import com.eljebo.common.utils.Const;
import com.eljebo.common.utils.ImageUtils;
import com.eljebo.common.utils.NetworkUtil;
import com.eljebo.common.utils.PrefStore;
import com.eljebo.customer.activity.CustomerMainActivity;
import com.eljebo.serviceprovider.activity.MainActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.gson.Gson;
import com.toxsl.volley.AppExpiredError;
import com.toxsl.volley.AppInMaintenance;
import com.toxsl.volley.AuthFailureError;
import com.toxsl.volley.NetworkError;
import com.toxsl.volley.ParseError;
import com.toxsl.volley.Request;
import com.toxsl.volley.ServerError;
import com.toxsl.volley.TimeoutError;
import com.toxsl.volley.VolleyError;
import com.toxsl.volley.toolbox.SyncEventListner;
import com.toxsl.volley.toolbox.SyncManager;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by tx-ubuntu-207 on 2/7/15.
 */
public class BaseActivity extends AppCompatActivity implements SyncEventListner, View.OnClickListener {

    public LayoutInflater inflater;
    public SyncManager syncManager;
    public PrefStore store;
    public PermCallback permCallback;

    private Dialog progressDialog;
    private TextView txtMsgTV;
    private int reqCode;
    private NetworksBroadcast networksBroadcast;
    private AlertDialog networkAlertDialog;
    private String networkStatus;
    private InputMethodManager inputMethodManager;
    private android.app.AlertDialog.Builder failureDailog;
    private android.app.AlertDialog failureAlertDialog;
    private boolean exit;
    private AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        syncManager = SyncManager.getInstance(this, !BuildConfig.DEBUG);
        syncManager.setBaseUrl(Const.SERVER_REMOTE_URL, getString(R.string.app_name));
        (BaseActivity.this).overridePendingTransition(R.anim.slide_in,
                R.anim.slide_out);
        inputMethodManager = (InputMethodManager) this
                .getSystemService(BaseActivity.INPUT_METHOD_SERVICE);
        store = new PrefStore(this);
        initializeNetworkBroadcast();

        strictModeThread();
        transitionSlideInHorizontal();
        progressDialog();
        failureDailog = new android.app.AlertDialog.Builder(this);
    }


    public void initFCM() {
//        if (checkPlayServices()) {
//            String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//            store.saveString(Const.DEVICE_TOKEN, refreshedToken);
//            if (refreshedToken != null) {
//                check(refreshedToken);
//            } else {
//                errorLogin();
//            }
//        }

        gotoLoginSignUpActivity();

    }

    public void gotoLoginSignUpActivity() {
        startActivity(new Intent(this, LoginSignUpActivity.class));
        finish();
    }

    public void gotoLoginSignUpActivityNew() {
        startActivity(new Intent(this, LoginSignUpActivity.class));
        finish();
    }

    public void gotoServiceProviderMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void gotoOtpFragment() {
        Fragment fragment = new OtpFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.login_frame, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void initializeNetworkBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        networksBroadcast = new NetworksBroadcast();
        registerReceiver(networksBroadcast, intentFilter);
    }

    public void setActionBarTitleInCenter(String title) {
        View view = inflater.inflate(R.layout.custom_action_bar, null);
        TextView titleTV = (TextView) view.findViewById(R.id.titleTV);
        titleTV.setText(title);

        ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(view, params);
    }


    private void unregisterNetworkBroadcast() {
        try {
            if (networksBroadcast != null) {
                unregisterReceiver(networksBroadcast);
            }
        } catch (IllegalArgumentException e) {
            networksBroadcast = null;
        }
    }

    private void showNoNetworkDialog(String status) {
        networkStatus = status;
        if (SnackbarManager.getCurrentSnackbar() != null) {
            SnackbarManager.getCurrentSnackbar().dismiss();
        }
        SnackbarManager.create(
                com.eljebo.common.snackBAr.Snackbar.with(this)
                        .type(SnackbarType.SINGLE_LINE)
                        .text(status).duration(com.eljebo.common.snackBAr.Snackbar
                        .SnackbarDuration.LENGTH_INDEFINITE)
                        .actionLabel("RETRY")
                        .actionListener(new ActionClickListener() {
                            @Override
                            public void onActionClicked(com.eljebo.common.snackBAr.Snackbar snackbar) {
                                if (!isNetworkAvailable()) {
                                    showNoNetworkDialog(networkStatus);
                                } else
                                    SnackbarManager.getCurrentSnackbar().dismiss();
                            }
                        })).show();
    }

    public String changeDateFormat(String dateString, String sourceDateFormat, String targetDateFormat) {
        if (dateString == null || dateString.isEmpty()) {
            return "";
        }
        SimpleDateFormat inputDateFromat = new SimpleDateFormat(sourceDateFormat, Locale.getDefault());
        Date date = new Date();
        try {
            date = inputDateFromat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(targetDateFormat, Locale.getDefault());
        return outputDateFormat.format(date);
    }

    public String changeDateFormatFromDate(Date sourceDate, String targetDateFormat) {
        if (sourceDate == null || targetDateFormat == null || targetDateFormat.isEmpty()) {
            return "";
        }
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(targetDateFormat, Locale.getDefault());
        return outputDateFormat.format(sourceDate);
    }

    protected void checkDate(String checkDate) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date serverDate = null;
        try {
            serverDate = dateFormat.parse(checkDate);
            cal.setTime(serverDate);
            Calendar currentcal = Calendar.getInstance();
            if (currentcal.after(cal)) {
                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
                builder.setMessage("Please contact : shiv@toxsl.com");
                builder.setTitle("Demo Expired");
                builder.setCancelable(false);
                builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        exitFromApp();
                    }
                });
                android.support.v7.app.AlertDialog alert = builder.create();
                alert.show();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, Const.PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                log(getString(R.string.this_device_is_not_supported));
                finish();
            }
            return false;
        }
        return true;
    }

    public boolean checkPermissions(String[] perms, int requestCode, PermCallback permCallback) {
        this.permCallback = permCallback;
        this.reqCode = requestCode;
        ArrayList<String> permsArray = new ArrayList<>();
        boolean hasPerms = true;
        for (String perm : perms) {
            if (ContextCompat.checkSelfPermission(this, perm) != PackageManager.PERMISSION_GRANTED) {
                permsArray.add(perm);
                hasPerms = false;
            }
        }
        if (!hasPerms) {
            String[] permsString = new String[permsArray.size()];
            for (int i = 0; i < permsArray.size(); i++) {
                permsString[i] = permsArray.get(i);
            }
            ActivityCompat.requestPermissions(BaseActivity.this, permsString, 99);
            return false;
        } else
            return true;
    }

    public InputFilter getEditTextFilter() {
        return new InputFilter() {

            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                boolean keepOriginal = true;
                StringBuilder sb = new StringBuilder(end - start);
                for (int i = start; i < end; i++) {
                    char c = source.charAt(i);
                    if (isCharAllowed(c)) // put your condition here
                        sb.append(c);
                    else
                        keepOriginal = false;
                }
                if (keepOriginal)
                    return null;
                else {
                    if (source instanceof Spanned) {
                        SpannableString sp = new SpannableString(sb);
                        TextUtils.copySpansFrom((Spanned) source, start, sb.length(), null, sp, 0);
                        return sp;
                    } else {
                        return sb;
                    }
                }
            }

            private boolean isCharAllowed(char c) {
                Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
                Matcher ms = ps.matcher(String.valueOf(c));
                return ms.matches();
            }
        };
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        boolean permGrantedBool = false;
        switch (requestCode) {
            case 99:
                for (int grantResult : grantResults) {
                    if (grantResult == PackageManager.PERMISSION_DENIED) {
                        showToast(getString(R.string.not_sufficient_permissions)
                                + getString(R.string.app_name)
                                + getString(R.string.permissionss));
                        permGrantedBool = false;
                        break;
                    } else {
                        permGrantedBool = true;
                    }
                }
                if (permCallback != null) {
                    if (permGrantedBool) {
                        permCallback.permGranted(reqCode);
                    } else {
                        permCallback.permDenied(reqCode);
                    }
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageUtils.activityResult(requestCode, resultCode, data);
    }

    public void exitFromApp() {
        finish();
        finishAffinity();
    }

    public String getUniqueDeviceId() {
        return Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public boolean hideSoftKeyboard() {
        try {
            if (getCurrentFocus() != null) {
                inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null
                && activeNetworkInfo.isConnectedOrConnecting();

    }

    public boolean isValidMail(String email) {
        return email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[!&^%$#@()=*/.+_-])(?=\\S+$).{8,}$");
    }

    public void keyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("KeyHash:>>>>>>>>>>>>>>>", "" + Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void log(String string) {
        if (BuildConfig.DEBUG)
            Log.e("BaseActivity", string);
    }

    private void progressDialog() {
        progressDialog = new Dialog(this);
        View view = View.inflate(this, R.layout.progress_dialog, null);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(view);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        txtMsgTV = (TextView) view.findViewById(R.id.txtMsgTV);
        progressDialog.setCancelable(false);
    }

    public void startProgressDialog() {
        if (progressDialog != null && !progressDialog.isShowing()) {
            try {
                progressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onSyncStart() {
        startProgressDialog();
    }

    @Override
    public void onSyncFinish() {
        stopProgressDialog();
    }

    SnackbarManager errorSnackBar(String errorString, final Request mRequest) {
        String buttontext;
        if (mRequest != null) {
            buttontext = "RETRY";
        } else {
            buttontext = "EXIT";
        }
        com.eljebo.common.snackBAr.Snackbar snackBar = com.eljebo.common.snackBAr.Snackbar.with(this)
                .type(SnackbarType.MULTI_LINE)
                .text(errorString)
                .duration(com.eljebo.common.snackBAr.Snackbar.SnackbarDuration.LENGTH_INDEFINITE)
                .actionLabel(buttontext)
                .actionListener(new ActionClickListener() {
                    @Override
                    public void onActionClicked(com.eljebo.common.snackBAr.Snackbar snackbar) {
                        if (mRequest != null) {
                            onSyncStart();
                            syncManager.getRequestQueue().add(mRequest);
                        } else {
                            finish();
                        }
                    }
                });
        return SnackbarManager.create(snackBar);
    }

    @Override
    public void onSyncFailure(VolleyError error, Request mRequest) {
        handleSyncError(error, mRequest);
    }

    private void handleSyncError(VolleyError error, Request mRequest) {
        if (this.isFinishing())
            return;
        if (failureAlertDialog != null && failureAlertDialog.isShowing())
            failureAlertDialog.dismiss();
        if (error instanceof NetworkError) {
            errorSnackBar(getString(R.string.request_timeout_slow_connection), mRequest).show();
        } else if (error instanceof ServerError) {
            showToast(getString(R.string.problem_connecting_to_the_server));
        } else if (error instanceof AuthFailureError) {
            showToast(getString(R.string.session_timeout_redirecting));
            syncManager.setLoginStatus(null);
            //--------------------------------------------------------------------------------------goToLogin------------------------------------------------
        } else if (error instanceof ParseError) {
            showToast(getString(R.string.bad_request));
        } else if (error instanceof TimeoutError) {
            errorSnackBar(getString(R.string.request_timeout_slow_connection), mRequest).show();
        } else if (error instanceof AppExpiredError)
            checkDate(error.getMessage());
        else if (error instanceof AppInMaintenance)
            failureErrorDialog(error.getMessage(), null).show();
        else if (!error.apiCrash.equals(""))
            showToast(error.apiCrash);
    }

    private android.app.AlertDialog failureErrorDialog(String errorString, final Request mRequest) {
        if (mRequest != null) {
            failureDailog.setMessage(errorString).setCancelable(false).setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).setPositiveButton("RETRY", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onSyncStart();
                    syncManager.getRequestQueue().add(mRequest);
                }
            });
        } else
            failureDailog.setMessage(errorString).setCancelable(false).setPositiveButton("EXIT", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        failureAlertDialog = failureDailog.create();
        return failureAlertDialog;
    }

    @Override
    public void onSyncSuccess(String controller, String action, boolean status, JSONObject jsonObject) {
    }

    public void showToast(String msg) {
        SnackbarManager.create(
                Snackbar.with(this).duration(Snackbar.SnackbarDuration.LENGTH_SHORT)
                        .type(SnackbarType.MULTI_LINE)
                        .text(msg)).show();
    }

    public void showToastOne(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

    }

    private void strictModeThread() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void transitionSlideInHorizontal() {
        this.overridePendingTransition(R.anim.slide_in_right,
                R.anim.slide_out_left);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterNetworkBroadcast();
    }

    public void backAction() {
        if (exit) {
            finishAffinity();
        } else {
            showToastOne(getString(R.string.press_one_more_time));
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 2 * 1000);
        }
    }

    public void saveUserProfileDataInPrefStore(ProfileData userProfileData) {
        String s = new Gson().toJson(userProfileData, ProfileData.class);
        store.saveString(Const.STORE_USER_PROFILE_DATA, s);
    }

    //get profile data from pref
    public ProfileData getprofileDataInPrefStore() {
        return new Gson().fromJson(store.getString(Const.STORE_USER_PROFILE_DATA), ProfileData.class);
    }

    public void gotoCustomerMainActivity() {
        startActivity(new Intent(this, CustomerMainActivity.class));
        finish();
    }

    //enable gps dialog
    public void buildAlertMessageNoGps() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        View dialogLayout = getLayoutInflater().inflate(R.layout.layout_enable_gps, null);
        alertDialogBuilder.setView(dialogLayout);
        TextView settingTV = (TextView) dialogLayout.findViewById(R.id.settingTV);
        alertDialog = alertDialogBuilder.create();

        settingTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
        alertDialog.show();
    }

    public interface PermCallback {
        void permGranted(int resultCode);

        void permDenied(int resultCode);
    }

    public class NetworksBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String status = NetworkUtil.getConnectivityStatusString(context);
            if (status == null && networkAlertDialog != null) {
                networkStatus = null;
                networkAlertDialog.dismiss();
            } else if (status != null && networkStatus == null)
                showNoNetworkDialog(status);
        }
    }
}
