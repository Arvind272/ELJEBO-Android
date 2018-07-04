package com.eljebo.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.eljebo.R;
import com.eljebo.common.activity.BaseActivity;
import com.eljebo.common.adapter.CountryDialogAdapter;
import com.eljebo.common.data.CountryData;
import com.eljebo.common.utils.AdapterListener;
import com.eljebo.common.utils.Const;
import com.eljebo.customer.fragment.CustomerSignUpFragment;
import com.eljebo.customer.fragment.ServiceProvidersFragment;
import com.eljebo.databinding.DialogCountryBinding;
import com.eljebo.serviceprovider.fragment.SignupFragment;
import com.eljebo.serviceprovider.new_bean.CountryDialogListDataBean;
import com.google.gson.Gson;
import com.toxsl.volley.Request;
import com.toxsl.volley.VolleyError;
import com.toxsl.volley.toolbox.SyncEventListner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by TOXSL\vinay.goyal on 16/6/18.
 */

public class CountryDialog extends Dialog implements SyncEventListner, View.OnClickListener, AdapterListener.CountryAdapterInterface {

    private BaseActivity baseActivity;
    private DialogCountryBinding binding;
    private int currentPage = 0;
    private boolean singleHit = false;
    private int id = 0, type = 0;
    private ArrayList<CountryData> countryDataArrayList = new ArrayList<>();

    private CountryData countryData;
    private CountryDialogAdapter countryDialogAdapter;
    private Fragment fragment;
    private String Type_Name;
    private AdapterListener adapterListener;

    private ArrayList<CountryDialogListDataBean> countryDialogListDataBeans = new ArrayList<>();

    public CountryDialog(@NonNull BaseActivity baseActivity, int id, int type, Fragment fragment) {
        super(baseActivity, R.style.animateDialog);

        this.baseActivity = baseActivity;
        this.id = id;
        this.type = type;
        this.fragment = fragment;

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        binding = DataBindingUtil.inflate(LayoutInflater.from(baseActivity), R.layout.dialog_country,
                null, false);
        setContentView(binding.getRoot());
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        init();
    }

    private void init() {
        adapterListener = AdapterListener.getInstance();
        adapterListener.setAdapterListener(this);
        InputMethodManager im = (InputMethodManager) baseActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (im != null) {
            im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.signUpRV.setLayoutManager(linearLayoutManager);
        currentPage = 0;
        singleHit = false;
        hitAPI();
        binding.crossIV.setOnClickListener(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.signUpRV.getContext(), LinearLayoutManager.VERTICAL);
        binding.signUpRV.addItemDecoration(dividerItemDecoration);

        binding.searchET.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    clearData();
                    if (type == Const.COUNTRY) {
                        Type_Name = "Country";
                    } else if (type == Const.STATE) {
                        Type_Name = "CityState";
                    } else {
                        Type_Name = "City";
                    }
                    if (binding.searchET.getText().toString().trim().isEmpty()) {
                        hitApi();
                    } else {
                        hitSearchAPI(Type_Name);
                    }

                    return true;
                }
                return false;
            }
        });

        binding.searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (binding.searchET.getText().toString().trim().length() >= 1) {
                    binding.crossIV.setVisibility(View.VISIBLE);
                } else {
                    binding.crossIV.setVisibility(View.GONE);
                }
                clearData();
                if (type == Const.COUNTRY) {
                    Type_Name = "Country";
                } else if (type == Const.STATE) {
                    Type_Name = "State";
                } else {
                    Type_Name = "City";
                }
                if (binding.searchET.getText().toString().trim().isEmpty()) {
                    hitApi();
                } else {
                    hitSearchAPI(Type_Name);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private void hitAPI() {
        if (type == Const.COUNTRY) {
            hitCountryApi();
        } else if (type == Const.STATE) {
            hitStateApi();
        } else if (type == Const.CITY) {
            hitCityApi();
        }
    }

    private void clearData() {
        currentPage = 0;
        singleHit = false;
        countryDataArrayList.clear();
        if (countryDialogAdapter != null) {
            countryDialogAdapter.notifyDataSetChanged();
        }
    }

    private void hitSearchAPI(String type_Name) {
        if (!singleHit) {
            singleHit = true;
            baseActivity.syncManager.sendJsonToServer(Const.API_SERVICE_SEARCH_REGION +
                    "?keyword=" + binding.searchET.getText().toString().trim() + "&type=" +
                    type_Name + "&page=" + currentPage, null, this);
        }
    }

    private void hitCountryApi() {
        if (!singleHit) {
            singleHit = true;
            baseActivity.syncManager.sendJsonToServer(Const.API_SERVICE_COUNTRY_LIST
                    , null, this);
        }
    }

    private void hitStateApi() {
        if (!singleHit) {
            singleHit = true;
            baseActivity.syncManager.sendJsonToServer(Const.API_SERVICE_STATE_LIST + "?country_id=" + id,
                    null, this);
        }
    }

    private void hitCityApi() {
        if (!singleHit) {
            singleHit = true;
            baseActivity.syncManager.sendJsonToServer(Const.API_SERVICE_CITY_LIST + "?state_id=" + id,
                    null, this);
        }
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
        try {
           /* if (jsonObject.getString("url").equalsIgnoreCase(Const.API_SERVICE_COUNTRY_LIST)
                    || jsonObject.getString("url").equalsIgnoreCase(Const.API_SERVICE_STATE_LIST)
                    || jsonObject.getString("url").equalsIgnoreCase(Const.API_SERVICE_CITY_LIST)
                    || jsonObject.getString("url").equalsIgnoreCase(Const.API_SERVICE_SEARCH_REGION)) {
                if (jsonObject.getInt("status") == Const.STATUSOK) {*/

                    if (currentPage == 0) {
                        countryDataArrayList.clear();
                    }
                  countryDataArrayList.clear();
            countryDialogListDataBeans.clear();

                    currentPage++;
                    JSONArray object = jsonObject.getJSONArray("data");
                    for (int i = 0; i < object.length(); i++) {

                        JSONObject jsonObjData = object.getJSONObject(i);


                        countryData = new Gson().fromJson(jsonObject.getJSONArray("data").
                                getJSONObject(i).toString(), CountryData.class);
                        countryDataArrayList.add(countryData);

                        if (jsonObject.getString("url").equals("getCountryList")) {
                            countryDialogListDataBeans.add(new CountryDialogListDataBean(
                                    jsonObjData.getString("id"),
                                    jsonObjData.getString("sortname"),
                                    jsonObjData.getString("name"),
                                    jsonObjData.getString("phonecode"),
                                    jsonObjData.getString("status")));

                        } else if (jsonObject.getString("url").equals("getStateList")) {
                            countryDialogListDataBeans.add(new CountryDialogListDataBean(
                                    jsonObjData.getString("id"),
                                    "",
                                    jsonObjData.getString("name"),
                                    "",
                                    jsonObjData.getString("country_id")));
                        } else if (jsonObject.getString("url").equals("getCityList")) {
                            countryDialogListDataBeans.add(new CountryDialogListDataBean(
                                    jsonObjData.getString("id"),
                                    "",
                                    jsonObjData.getString("name"),
                                    "",
                                    jsonObjData.getString("state_id")));
                        }
                    }

                    if (currentPage >= jsonObject.optInt("total_pages")) {
                        singleHit = true;
                    } else {
                        singleHit = false;
                    }

                    setAdapter();
               /* } else {
                    baseActivity.showToastOne(jsonObject.getString("error"));
                }
            }*/
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setAdapter() {
        if (countryDialogAdapter == null) {
            countryDialogAdapter = new CountryDialogAdapter(this, countryDataArrayList, fragment, adapterListener,
                    countryDialogListDataBeans);
            binding.signUpRV.setAdapter(countryDialogAdapter);
        } else {
            countryDialogAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.crossIV:
                binding.searchET.setText("");
                clearData();
                hitAPI();
                break;
        }
    }

    public void setData(int adapterPosition) {
        InputMethodManager im = (InputMethodManager) baseActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (im != null) {
            im.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
        if (fragment instanceof SignupFragment) {
            ((SignupFragment) fragment).setResidenceData(
                    Integer.parseInt(countryDialogListDataBeans.get(adapterPosition).getId()),
                    countryDialogListDataBeans.get(adapterPosition).getName());
        } else if (fragment instanceof CustomerSignUpFragment) {
            ((CustomerSignUpFragment) fragment).setResidenceData(
                    Integer.parseInt(countryDialogListDataBeans.get(adapterPosition).getId()),
                    countryDialogListDataBeans.get(adapterPosition).getName());
        } else if (fragment instanceof ServiceProvidersFragment){
            ((ServiceProvidersFragment) fragment).setResidenceData(
                    Integer.parseInt(countryDialogListDataBeans.get(adapterPosition).getId()),
                    countryDialogListDataBeans.get(adapterPosition).getName());
        }
    }


    @Override
    public void hitApi() {
        hitAPI();
    }
}
