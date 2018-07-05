package com.eljebo.serviceprovider.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.common.fragment.ChangePasswordFragment;
import com.eljebo.common.utils.Const;
import com.eljebo.customer.activity.CustomerMainActivity;
import com.eljebo.customer.fragment.ServiceProvidersFragment;
import com.eljebo.databinding.FragmentProfileBinding;
import com.eljebo.serviceprovider.activity.MainActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

/**
 * Created by TOXSL\himanshu.gulati on 12/6/18.
 */

public class ProfileFragment extends BaseFragment {
    private View view;
    private FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setTitle(getString(R.string.profile));
        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(
                    inflater, R.layout.fragment_profile, container, false);
            return binding.getRoot();
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        baseActivity.hideSoftKeyboard();
        initUI();
    }

    private void initUI() {
        getUserInfoDetail();
        binding.personalInfoTV.setOnClickListener(this);
        binding.changePasswordTV.setOnClickListener(this);
        binding.servicesTV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Fragment frag = null;
        switch (v.getId()) {
            case R.id.personalInfoTV:
                frag = new PersonalInfoFragment();
                break;
            case R.id.changePasswordTV:
                frag = new ChangePasswordFragment();
                break;
            case R.id.servicesTV:
                frag = new ServicesFragment();
                break;
        }

        if (frag != null) {
            baseActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, frag)
                    .addToBackStack(null)
                    .commit();
        }
    }


    public void getUserInfoDetail() {

        final ACProgressFlower acProgressFlower = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();
        acProgressFlower.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST,
                Const.NEW_BASE_URL
                        + "getUserInfo",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            RequestQueue queue = Volley.newRequestQueue(getActivity());
                            queue.getCache().clear();
                            Log.e("getUserInfoDetail==>>", response);
                            JSONObject json = new JSONObject(response);
                            String message = json.getString("message");

                            if (json.getString("status").equals("1")){

                                JSONObject jsonObjData = json.getJSONObject("data");
                                // String

                                String firstname = "";
                                String lastname = "";
                                String email = "";
                                String mobile = "";
                                String address = "";

                                String profile_pic = "";

                                if (!jsonObjData.isNull("firstname")){
                                    firstname = jsonObjData.getString("firstname");

                                    Const.saveData(getActivity(),
                                            "firstNameSerProviderUser", firstname);
                                }

                                if (!jsonObjData.isNull("lastname")){
                                    lastname = jsonObjData.getString("lastname");
                                    Const.saveData(getActivity(),
                                            "lastNameSerProviderUser", lastname);
                                }

                                if(!jsonObjData.isNull("email")){
                                    email = jsonObjData.getString("email");
                                    Const.saveData(getActivity(),
                                            "emailSerProviderUser", email);
                                }
                                if(!jsonObjData.isNull("mobile")){
                                    mobile = jsonObjData.getString("mobile");
                                    Const.saveData(getActivity(),
                                            "mobileSerProviderUser", mobile);

                                }
                                if(!jsonObjData.isNull("address")){
                                    address = jsonObjData.getString("address");
                                    Const.saveData(getActivity(),
                                            "addressSerProviderUser", address);

                                }

                                if(!jsonObjData.isNull("profile_pic")){
                                    profile_pic = jsonObjData.getString("profile_pic");
                                    Picasso.with(getActivity())
                                            .load(profile_pic)
                                            .placeholder(R.mipmap.ic_pic_home)
                                            .error(R.mipmap.ic_pic_home)
                                            .into(binding.mealImageOrder);
                                }

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

                Log.e("getUserDetailData", "Params==>> " + params);

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
