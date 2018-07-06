package com.eljebo.common.utils;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.eljebo.R;
import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import org.acra.ACRA;
import org.acra.ReportField;
import org.acra.ReportingInteractionMode;
import org.acra.annotation.ReportsCrashes;


/**
 * Created by samosys on 8/4/16.
 */


@ReportsCrashes(mailTo = "arvindmali272@gmail.com", customReportContent = {
        ReportField.APP_VERSION_CODE, ReportField.APP_VERSION_NAME,
        ReportField.ANDROID_VERSION, ReportField.PHONE_MODEL,
        ReportField.CUSTOM_DATA, ReportField.STACK_TRACE, ReportField.LOGCAT},
        mode = ReportingInteractionMode.TOAST, resToastText = R.string.crash_toast_text)

public class UILApplication extends MultiDexApplication { //MultiDexApplication
    Context mContext;



    public UILApplication() {
    }

    @Override
    public void onCreate() {
        FirebaseApp.initializeApp(this);
        Firebase.setAndroidContext(this);
        mContext = getApplicationContext();

       // sAnalytics = GoogleAnalytics.getInstance(this);

        //   CustomActivityOnCrash.install(this);

       /* if (AppConstants.Config.DEVELOPER_MODE && Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyDialog().build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyDeath().build());
        }*/
        super.onCreate();
        ACRA.init(this);

        FirebaseApp.initializeApp(this);
        Firebase.setAndroidContext(this);

        if(!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
           MultiDex.install(this);

    }
    public static synchronized UILApplication getInstance() {
        return UILApplication.getInstance();
    }
}