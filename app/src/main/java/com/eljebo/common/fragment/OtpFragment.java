package com.eljebo.common.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.eljebo.common.activity.LoginSignUpActivity;
import com.eljebo.common.data.ProfileData;
import com.eljebo.common.utils.Const;
import com.eljebo.databinding.FragmentOtpBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

/**
 * Created by TOXSL\himanshu.gulati on 11/6/18.
 */

public class OtpFragment extends BaseFragment {
    private View view;
    private FragmentOtpBinding binding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil
                    .inflate(inflater, R.layout.fragment_otp, container, false);
            return binding.getRoot();
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        baseActivity.hideSoftKeyboard();
        ((LoginSignUpActivity) baseActivity).setToolbar("", false);
        initUI();
    }

    private void initUI() {
        binding.sendBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendBT:
                baseActivity.hideSoftKeyboard();
                validate();
               // sendDataToServer();
                break;
        }
    }

    private void validate() {
        if (binding.codeET.getText().toString().trim().isEmpty()) {
            showToastOne(baseActivity.getString(R.string.enter_6_digit_otp_code_sent_to_your_email));
        } else {
            doOtpServiceHit();
           // sendDataToServer();
        }
    }

    private void sendDataToServer() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            baseActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.login_frame, new LoginFragment())
                    .commit();
        } else if (store.getInt("role") == Const.ROLE_USER) {
            baseActivity.gotoCustomerMainActivity();
        } else {
            baseActivity.gotoServiceProviderMainActivity();
        }
    }


    public void doOtpServiceHit() {

        final ACProgressFlower acProgressFlower = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();
        acProgressFlower.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, Const.NEW_BASE_URL
                + "verifyOtp",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            RequestQueue queue = Volley.newRequestQueue(getActivity());
                            queue.getCache().clear();
                            Log.e("OtpResponse==>>", response);
                            JSONObject json = new JSONObject(response);
                            String message = json.getString("message");

                            if (json.getString("status").equals("1")){

                                sendDataToServer();

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

                //otp ,user_id

                Map<String, String> params = new HashMap<>();
                params.put("otp", binding.codeET.getText().toString().trim());
                params.put("user_id", Const.loadData(getActivity(), "loginUserId"));//

                Log.e("Otp", "Params==>> " + params);

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
