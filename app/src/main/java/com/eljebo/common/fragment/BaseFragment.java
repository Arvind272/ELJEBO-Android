package com.eljebo.common.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import com.eljebo.common.activity.BaseActivity;
import com.eljebo.common.utils.PrefStore;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.toxsl.volley.Request;
import com.toxsl.volley.VolleyError;
import com.toxsl.volley.toolbox.SyncEventListner;
import com.toxsl.volley.toolbox.SyncManager;

import org.json.JSONObject;

/**
 * Created by TOXSL\neeraj.narwal on 2/2/16.
 */
public class BaseFragment extends Fragment implements AdapterView.OnItemClickListener,
        View.OnClickListener, SyncEventListner, AdapterView.OnItemSelectedListener,
        CompoundButton.OnCheckedChangeListener {

    public BaseActivity baseActivity;
    public PrefStore store;
    public SyncManager syncManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = (BaseActivity) getActivity();
        baseActivity = (BaseActivity) getActivity();
        syncManager = baseActivity.syncManager;
        store = baseActivity.store;

    }

    @Override
    public void onResume() {
        super.onResume();
        baseActivity.hideSoftKeyboard();
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onClick(View v) {

    }

    public void showToast(String msg) {
        baseActivity.showToast(msg);
    }

    public void showToastOne(String s) {
        baseActivity.showToastOne(s);
    }

    @Override
    public void onSyncStart() {
        baseActivity.onSyncStart();
    }

    @Override
    public void onSyncFinish() {
        baseActivity.onSyncFinish();
    }

    @Override
    public void onSyncFailure(VolleyError error, Request mRequest) {
        baseActivity.onSyncFailure(error, mRequest);
    }

    public void log(String s) {
        baseActivity.log(s);
    }

    @Override
    public void onSyncSuccess(String controller, String action, boolean status, JSONObject jsonObject) {
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    public void callGoogleSearch(int reqCode) {
        Intent intent;
        try {
            intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY).build(baseActivity);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivityForResult(intent, reqCode);
        } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

}
