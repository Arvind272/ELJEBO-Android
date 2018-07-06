package com.eljebo.customer.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.eljebo.R;
import com.eljebo.common.adapter.ExpandableServiceAdapter;
import com.eljebo.common.data.ServiceData;
import com.eljebo.common.data.SubService;
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.common.utils.Const;
import com.eljebo.customer.activity.CustomerMainActivity;
import com.eljebo.databinding.FragmentCustomerHomeBinding;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by TOXSL\vinay.goyal on 12/6/18.
 */

public class CustomerHomeFragment extends BaseFragment {

    private View view;
    private FragmentCustomerHomeBinding binding;
    private ArrayList<SubService> selectedServiceData = new ArrayList<>();
    private ArrayList<ServiceData> serviceDatas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_customer_home, container, false);
            return binding.getRoot();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hitServicesApi();
        ((CustomerMainActivity) getActivity()).setTitle(getString(R.string.home));
        this.view = view;
        baseActivity.hideSoftKeyboard();
        initUI();
    }

    private void initUI() {
        serviceListFunctionality();
        binding.searchServiceBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {
            case R.id.searchServiceBT:
                baseActivity.hideSoftKeyboard();
                baseActivity.store.setData("selectedServices", selectedServiceData);
                Log.e("selectedServices", "selectedServices==> " +selectedServiceData.size());

                for (int i=0;i<selectedServiceData.size();i++){
                    Log.e("selectedServices", "item==> " +selectedServiceData.get(i).id);
                }

                if(selectedServiceData.size()>0){
                    gotoServiceProviderFragment();
                }else {
                    Toast.makeText(getActivity(), "Please select at least one service",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    private void hitServicesApi() {
        baseActivity.syncManager.sendJsonToServer(Const.API_SERVICE_LIST,
                null, this);
    }

    private void gotoServiceProviderFragment() {

        baseActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.customer_container, new ServiceProvidersFragment())
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onSyncSuccess(String controller, String action, boolean status, JSONObject jsonObject) {
        super.onSyncSuccess(controller, action, status, jsonObject);
        try {
            if (jsonObject.getString("url").equals(Const.API_SERVICE_LIST)) {
              //  if (jsonObject.getInt("status") == Const.STATUSOK) {
                    serviceDatas.clear();
                    for (int k = 0; k < jsonObject.getJSONArray("data").length(); k++) {
                        ServiceData serviceData = new Gson().fromJson(jsonObject.getJSONArray("data")
                                .getJSONObject(k).toString(), ServiceData.class);
                        serviceDatas.add(serviceData);
                    }
                    setServiceAdapter();
                /*} else {
                    baseActivity.showToastOne(jsonObject.getString("error"));
                }*/

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void checkServiceData(int groupPostion, int childPosition) {
        serviceDatas.get(groupPostion).subServices.get(childPosition).isChecked = !serviceDatas.get(groupPostion).subServices.get(childPosition).isChecked;
        selectedServiceData.clear();
        for (int g = 0; g < serviceDatas.size(); g++) {
            for (int c = 0; c < serviceDatas.get(g).subServices.size(); c++) {
                if (serviceDatas.get(g).subServices.get(c).isChecked) {
                    SubService service = new SubService();
                    service.id = serviceDatas.get(g).subServices.get(c).id;
                    service.title = serviceDatas.get(g).subServices.get(c).title;
                    service.price = "";
                    selectedServiceData.add(service);
                }
            }
        }
    }

    private void setServiceAdapter() {
        ExpandableServiceAdapter adapter = new ExpandableServiceAdapter(baseActivity,
                serviceDatas, this);
        binding.servicesELV.setAdapter(adapter);
    }

    private void serviceListFunctionality() {
        binding.servicesELV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                for (int k = 0; k < serviceDatas.size(); k++) {
                    if (k != i) {
                        serviceDatas.get(k).isExpand = false;
                        binding.servicesELV.collapseGroup(k);
                    } else {
                        serviceDatas.get(k).isExpand = true;
                    }
                }
            }
        });
        binding.servicesELV.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                serviceDatas.get(i).isExpand = false;

            }
        });
    }

}
