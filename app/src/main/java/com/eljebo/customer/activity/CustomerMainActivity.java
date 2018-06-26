package com.eljebo.customer.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.eljebo.R;
import com.eljebo.common.activity.BaseActivity;
import com.eljebo.common.fragment.NotificationFragment;
import com.eljebo.customer.fragment.CustomerHomeFragment;
import com.eljebo.customer.fragment.CustomerProfileFragment;
import com.eljebo.serviceprovider.adapter.DrawerAdapter;
import com.eljebo.serviceprovider.data.DrawerData;

import java.util.ArrayList;

/**
 * Created by TOXSL\vinay.goyal on 12/6/18.
 */

public class CustomerMainActivity extends BaseActivity {
    private DrawerLayout drawer;
    AdapterView.OnItemClickListener seekerDrawerListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Fragment frag = null;
            switch (position) {
                case 0:
                    frag = new CustomerProfileFragment();
                    break;
                case 1:
                    frag = new NotificationFragment();
                    break;
                case 2:
                    store.cleanPref();
                    logout();
                    break;


            }
            if (frag != null)
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.customer_container, frag)
                        .commit();
            drawer.closeDrawers();
        }
    };
    private ImageView profilePicCIV;
    private TextView nameTV;
    private ListView drawerLV;
    private ActionBarDrawerToggle toggle;
    private ArrayList<DrawerData> drawerItems = new ArrayList<>();
    private TextView titleTV;

    private void logout() {
        gotoLoginSignUpActivity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);
        setToolBar();
        init();
        initDrawer();
        gotoCustomerHomeFragment();

    }


    private void setToolBar() {
        Toolbar toolbarTB = (Toolbar) findViewById(R.id.toolbarTB);
        titleTV = (TextView) findViewById(R.id.titleTV);
        titleTV.setText(getString(R.string.app_name));
        setSupportActionBar(toolbarTB);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    public void setTitle(String title) {
        if (titleTV != null) {
            titleTV.setText(title);
        }
    }


    private void gotoCustomerHomeFragment() {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        Fragment fragment = new CustomerHomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.customer_container, fragment)
                .commit();
    }
    ////////////////////////////////////////////////////


    private void init() {
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        LinearLayout headLL = (LinearLayout) findViewById(R.id.headLL);
        profilePicCIV = (ImageView) findViewById(R.id.profilePicCIV);
        nameTV = (TextView) findViewById(R.id.nameTV);
        headLL.requestLayout();
        headLL.setOnClickListener(this);
        drawerLV = (ListView) findViewById(R.id.drawerLV);
        toggle = new ActionBarDrawerToggle(this, drawer, null,
                R.string.app_name, R.string.app_name) {

            @SuppressLint("RestrictedApi")
            @Override
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                getSupportActionBar().setDisplayShowHomeEnabled(false);
                getSupportActionBar().setHomeButtonEnabled(false);
                titleTV.setVisibility(View.GONE);
                invalidateOptionsMenu();
            }

            @SuppressLint("RestrictedApi")
            @Override
            public void onDrawerClosed(View drawerView) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setDisplayShowHomeEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
                titleTV.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
            }
        };
        drawer.addDrawerListener(toggle);
        updateDrawer();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }


    private void updateDrawer() {
//        profilePicCIV.setImageUrl(store.getString(Const.IMAGE));
//        nameTV.setText("My Name");
    }

    private void initDrawer() {
        Integer icons[] = {R.mipmap.ic_profile_drawer
                , R.mipmap.ic_not_drawer
                , R.mipmap.ic_logout};
        String[] names = getResources().getStringArray(R.array.customer_drawer_items);
        for (int i = 0; i < icons.length; i++) {
            DrawerData drawerData = new DrawerData();
            drawerData.icon = icons[i];
            drawerData.name = names[i];
            drawerItems.add(drawerData);
        }
        DrawerAdapter drawerAdapter = new DrawerAdapter(this, 0, drawerItems);
        drawerLV.setAdapter(drawerAdapter);
        drawerLV.setOnItemClickListener(seekerDrawerListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        toggle.setDrawerIndicatorEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.customer_container);
        if (fragment instanceof CustomerHomeFragment) {
            toggle.setDrawerIndicatorEnabled(true);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNDEFINED);
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_menu);
        } else {
            toggle.setDrawerIndicatorEnabled(false);
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_back);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        hideSoftKeyboard();
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (fragment instanceof CustomerHomeFragment) {
            backAction();
        } else {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            } else {
                gotoCustomerHomeFragment();
            }

        }
    }
}