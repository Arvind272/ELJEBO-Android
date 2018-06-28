package com.eljebo.common.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.eljebo.common.activity.BaseActivity;
import com.eljebo.R;
import com.eljebo.common.utils.Const;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;

/**
 * Created by TOXSL\chirag.tyagi on 8/2/18.
 */

public class SplashActivity extends BaseActivity {

    String devicetoken = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_splash);

        /*devicetoken = FirebaseInstanceId.getInstance().getToken();
        Log.e("devicetoken", "devicetoken==>> " +devicetoken);*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initFCM();
            }
        }, Const.SPLASH_TIMEOUT);
    }
}
