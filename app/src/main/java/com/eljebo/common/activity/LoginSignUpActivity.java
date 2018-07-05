package com.eljebo.common.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.eljebo.R;
import com.eljebo.common.fragment.AddCardFragment;
import com.eljebo.common.fragment.RoleSelectionFragment;
import com.eljebo.common.fragment.TermsAndServicesFragment;
import com.eljebo.customer.fragment.PaymentFragment;

/**
 * Created by TOXSL\chirag.tyagi on 21/2/18.
 */

public class LoginSignUpActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        setToolbar("", false);


        gotoRoleSelectionFragment();
    }

    private void gotoRoleSelectionFragment() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        Fragment fragment = new RoleSelectionFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_frame, fragment)
                .commit();

    }

    public void setToolbar(String title, boolean enable) {

        Toolbar toolbarTB = findViewById(R.id.toolbarTB);
        setSupportActionBar(toolbarTB);
        TextView titleTV = (TextView) findViewById(R.id.titleTV);

        if (enable) {
            toolbarTB.setVisibility(View.VISIBLE);

        }else {

            toolbarTB.setVisibility(View.GONE);
        }

        titleTV.setText(title);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.login_frame);
        if (fragment instanceof AddCardFragment || fragment instanceof PaymentFragment || fragment instanceof TermsAndServicesFragment) {
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_back);
        } else {
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.login_frame);
        if (fragment instanceof RoleSelectionFragment) {
            backAction();
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            gotoRoleSelectionFragment();
        }
    }
}
