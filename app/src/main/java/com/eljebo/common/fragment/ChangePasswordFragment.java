package com.eljebo.common.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eljebo.R;
import com.eljebo.common.utils.Const;
import com.eljebo.customer.activity.CustomerMainActivity;
import com.eljebo.databinding.FragmentChangePasswordBinding;
import com.eljebo.serviceprovider.activity.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

/**
 * Created by TOXSL\himanshu.gulati on 11/6/18.
 */

public class ChangePasswordFragment extends BaseFragment {
    private View view;
    private FragmentChangePasswordBinding binding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil
                    .inflate(inflater, R.layout.fragment_change_password, container, false);
            return binding.getRoot();
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        baseActivity.hideSoftKeyboard();
        if (store.getInt("role") == Const.ROLE_USER) {
            ((CustomerMainActivity) baseActivity).setTitle(getString(R.string.change_password));
        } else {
            ((MainActivity) baseActivity).setTitle(getString(R.string.change_password));
        }
        initUI();
    }

    private void initUI() {
        binding.confirmBT.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getWindow().setSoftInputMode(WindowManager.
                LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirmBT:
                baseActivity.hideSoftKeyboard();
                validate();
                break;
        }
    }

    private void validate() {
        if (binding.oldPassET.getText().toString().trim().isEmpty()) {
            showToastOne("Please enter old password");
        } else if (binding.newPassET.getText().toString().trim().isEmpty()) {
            showToastOne("Please enter new password");
        } else {
            changePasswordForBothUser();
        }
    }


    public void changePasswordForBothUser() {

        final ACProgressFlower acProgressFlower = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();
        acProgressFlower.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST,
                Const.NEW_BASE_URL
                + "changeUserPassword",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            RequestQueue queue = Volley.newRequestQueue(getActivity());
                            queue.getCache().clear();
                            Log.e("changePasswordForResponse==>>", response);
                            JSONObject json = new JSONObject(response);
                            String message = json.getString("message");

                            if (json.getString("status").equals("1")){

                                binding.oldPassET.setText("");
                                binding.newPassET.setText("");
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

                //user_id, token, old_password,new_password

                Map<String, String> params = new HashMap<>();
                params.put("user_id", Const.loadData(getActivity(), "loginUserId"));
                params.put("token", Const.loadData(getActivity(), "loginUserToken"));
                params.put("old_password", binding.oldPassET.getText().toString().trim());
                params.put("new_password", binding.newPassET.getText().toString().trim());

                Log.e("changePassword", "Params==>> " + params);

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
