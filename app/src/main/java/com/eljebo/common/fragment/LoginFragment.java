package com.eljebo.common.fragment;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eljebo.BuildConfig;
import com.eljebo.R;
import com.eljebo.common.activity.LoginSignUpActivity;
import com.eljebo.common.data.ProfileData;
import com.eljebo.common.utils.Api2Params;
import com.eljebo.common.utils.Const;
import com.eljebo.customer.fragment.CustomerSignUpFragment;
import com.eljebo.databinding.FragmentLoginBinding;
import com.eljebo.serviceprovider.fragment.SignupFragment;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by TOXSL\chirag.tyagi on 21/2/18.
 */

public class LoginFragment extends BaseFragment {
    private View view;
    private FragmentLoginBinding binding;
    private int role;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            role = getArguments().getInt("role");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(
                    inflater, R.layout.fragment_login, container, false);
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
        if (BuildConfig.DEBUG) {
            binding.emailET.setText("");
            binding.passwordET.setText("");
        }
        binding.loginBT.setOnClickListener(this);
        binding.forPasswordTV.setOnClickListener(this);
        binding.signUpLL.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginBT:
                baseActivity.hideSoftKeyboard();
               // baseActivity.gotoOtpFragment();
                validate();
                break;
            case R.id.forPasswordTV:
                baseActivity.hideSoftKeyboard();

                gotoForgotPasswordFragment();
                break;
            case R.id.signUpLL:
                baseActivity.hideSoftKeyboard();
                if (role == Const.ROLE_PROVIDER) {
                    gotoSignUpFragment();
                } else {
                    gotoSignUpCustomer();
                }
                break;
        }
    }

    private void validate() {
        if (binding.emailET.getText().toString().trim().isEmpty()) {
           // showToastOne(baseActivity.getString(R.string.enter_username));
            showToast(baseActivity.getString(R.string.enter_username));

        } else if (binding.passwordET.getText().toString().isEmpty()) {
           // showToastOne(baseActivity.getString(R.string.enter_password));
            showToast(baseActivity.getString(R.string.enter_password));
        } else {
           // sendDataToServer();
            doSignUpFromServer();
        }
    }

    private void sendDataToServer() {

        //username,password,device_type,device_token , user_type

        Map<String, String> params = new HashMap<>();
        params.put("username", binding.emailET.getText().toString().trim());
        params.put("password", binding.passwordET.getText().toString().trim());
        String refreshedToken = baseActivity.getUniqueDeviceId();
        params.put("device_token", refreshedToken);
        params.put("device_type", "1");
        // params.put("device_name", String.valueOf(Build.MODEL));
        params.put("user_type", "2");

        /*Api2Params params = new Api2Params();
        params.put("username", binding.emailET.getText().toString().trim());
        params.put("password", binding.passwordET.getText().toString().trim());
        String refreshedToken = baseActivity.getUniqueDeviceId();
        params.put("device_token", refreshedToken);
        params.put("device_type", "2");
       // params.put("device_name", String.valueOf(Build.MODEL));
        params.put("role_id", "2");*/
        Log.e("Login", "params==>> " +params);
        syncManager.sendJsonToServer(Const.API_LOGIN, params.toString(), this);
    }


    public void doSignUpFromServer() {

        StringRequest postRequest = new StringRequest(Request.Method.POST, Const.NEW_BASE_URL
                + "login",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            RequestQueue queue = Volley.newRequestQueue(getActivity());
                            queue.getCache().clear();
                            Log.e("LoginResponse==>>", response);
                            JSONObject json = new JSONObject(response);
                            String message = json.getString("message");

                            if (json.getString("status").equals("1")){

                                baseActivity.gotoOtpFragment();
                            } else {

                            }

                            showToast(""+message);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                })
        {

            @Override
            protected Map<String, String> getParams() {
                //username,password,device_type,device_token , user_type
                Map<String, String> params = new HashMap<>();
                params.put("username", binding.emailET.getText().toString().trim());
                params.put("password", binding.passwordET.getText().toString().trim());
                String refreshedToken = baseActivity.getUniqueDeviceId();
                params.put("device_token", refreshedToken);
                params.put("device_type", "1");
                params.put("user_type", "2");

                Log.e("Login", "Params==>> " + params);

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


    private void gotoSignUpCustomer() {
        baseActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_frame, new CustomerSignUpFragment()
                )
                .addToBackStack(null)
                .commit();
    }

    private void gotoForgotPasswordFragment() {

        baseActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_frame, new ForgotPasswordFragment())
                .addToBackStack(null)
                .commit();
    }

    private void gotoSignUpFragment() {
        baseActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_frame, new SignupFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onSyncSuccess(String controller, String action, boolean status, JSONObject jsonObject) {
        super.onSyncSuccess(controller, action, status, jsonObject);
        try {
            if (jsonObject.getString("url").equalsIgnoreCase(Const.API_LOGIN)) {
                if (jsonObject.getInt("status") == Const.STATUSOK) {
                    if (jsonObject.has("user_detail")) {
                        try {
                            JSONObject object = jsonObject.getJSONObject("user_detail");
                            ProfileData profileData = new Gson().fromJson(object.toString(), ProfileData.class);
                            baseActivity.saveUserProfileDataInPrefStore(profileData);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        baseActivity.gotoOtpFragment();
                    }
                } else {
                    showToastOne(jsonObject.getString("error"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
