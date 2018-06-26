package com.eljebo.common.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.eljebo.common.activity.BaseActivity;
import com.eljebo.R;
import com.eljebo.common.utils.Const;

/**
 * Created by TOXSL\chirag.tyagi on 8/2/18.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
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
