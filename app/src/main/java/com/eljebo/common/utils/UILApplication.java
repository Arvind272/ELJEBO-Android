package com.eljebo.common.utils;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by samosys on 8/4/16.
 */
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