package com.eljebo.customer.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eljebo.R;
import com.eljebo.common.data.SubService;
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.common.fragment.LoginFragment;
import com.eljebo.common.utils.Const;
import com.eljebo.customer.activity.CustomerMainActivity;
import com.eljebo.customer.adapter.ServiceProvidersAdapter;
import com.eljebo.customer.cus_new_bean.ServiceProviderListBean;
import com.eljebo.databinding.FragmentServiceProvidersBinding;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

/**
 * Created by TOXSL\vinay.goyal on 12/6/18.
 */

public class ServiceProvidersFragment extends BaseFragment {

    private View view;
    private FragmentServiceProvidersBinding binding;
    private String selectedSubServiceIds = "";
    private ArrayList<ServiceProviderListBean> serviceProviderListBeans;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_service_providers, container, false);
            return binding.getRoot();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((CustomerMainActivity) baseActivity).setTitle(getString(R.string.list_of_service_providers));
        this.view = view;

        initUI();
    }

    private void initUI() {

        serviceProviderListBeans = new ArrayList<>();

        ArrayList<SubService> subServices = baseActivity.store.<SubService>getData("selectedServices");
        Log.e("getSelectedSubService", "==> " +subServices.size());

        for (int i=0;i<subServices.size();i++){
            Log.e("getSelectedSubService", "ids==> " +subServices.get(i).id);

            if (i==0){
                selectedSubServiceIds = subServices.get(i).id.toString();
            } else {
                selectedSubServiceIds = selectedSubServiceIds+","+subServices.get(i).id;
            }
        }
        Log.e("getSelectedSubService", "selectedSubServiceIds==> " +selectedSubServiceIds);

        binding.serviceProviderRV.setLayoutManager(new LinearLayoutManager(baseActivity));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.serviceProviderRV.getContext(),
                LinearLayoutManager.VERTICAL);
        binding.serviceProviderRV.addItemDecoration(dividerItemDecoration);
        /*ServiceProvidersAdapter serviceProvidersAdapter = new ServiceProvidersAdapter(this);
        binding.serviceProviderRV.setAdapter(serviceProvidersAdapter);*/

        getServiceProviderList(this);


    }

    public void setClick(int pos) {
        baseActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.customer_container, new ServiceProviderLocationFragment())
                .addToBackStack(null)
                .commit();
    }


    public void getServiceProviderList(final ServiceProvidersFragment serviceProvidersFragment) {

        final ACProgressFlower acProgressFlower = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();
        acProgressFlower.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, Const.NEW_BASE_URL
                + "serviceProviderList",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            RequestQueue queue = Volley.newRequestQueue(getActivity());
                            queue.getCache().clear();
                            serviceProviderListBeans.clear();
                            Log.e("getServiceProviderListResponse==>>", response);
                            JSONObject json = new JSONObject(response);
                            String message = json.getString("message");

                            if (json.getString("status").equals("1")){

                                JSONArray jsonArrData = json.getJSONArray("data");
                                for (int i=0;i<jsonArrData.length();i++){

                                    JSONObject jsonObjData = jsonArrData.getJSONObject(i);

                                    String user_id = "";
                                    String name = "";
                                    String profile_pic = "";

                                    if (!jsonObjData.isNull("user_id")){
                                        user_id = jsonObjData.getString("user_id");
                                    }
                                    if (!jsonObjData.isNull("name")){
                                        name = jsonObjData.getString("name");
                                    }
                                    if (!jsonObjData.isNull("profile_pic")){
                                        profile_pic = jsonObjData.getString("profile_pic");
                                    }


                                    serviceProviderListBeans.add(new ServiceProviderListBean(user_id,
                                            name, profile_pic));
                                }

                                ServiceProvidersAdapter serviceProvidersAdapter = new ServiceProvidersAdapter(
                                        serviceProvidersFragment, serviceProviderListBeans, getActivity());
                                binding.serviceProviderRV.setAdapter(serviceProvidersAdapter);
                                serviceProvidersAdapter.notifyDataSetChanged();

                            } else {
                            }

                            showToast(""+message);
                            if (acProgressFlower.isShowing()){
                                acProgressFlower.dismiss();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            if (acProgressFlower.isShowing()){
                                acProgressFlower.dismiss();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        if (acProgressFlower.isShowing()){
                            acProgressFlower.dismiss();
                        }
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                //user_id , token , service_ids
                Map<String, String> params = new HashMap<>();
                params.put("user_id", Const.loadData(getActivity(), "loginUserId"));
                params.put("token", Const.loadData(getActivity(), "loginUserToken"));
                params.put("service_ids", selectedSubServiceIds);

                Log.e("getServiceProviderList", "Params==>> " + params);

                return params;
            }
        };

        Volley.newRequestQueue(getActivity()).add(postRequest);
        Log.e("LOGIN", postRequest.toString());
        postRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
    }

}
