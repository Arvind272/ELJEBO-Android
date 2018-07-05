package com.eljebo.common.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.location.Location;
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
import com.eljebo.common.activity.LoginSignUpActivity;
import com.eljebo.common.data.ProfileData;
import com.eljebo.common.utils.Const;
import com.eljebo.common.utils.GoogleApisHandle;
import com.eljebo.databinding.FragmentTermsBinding;
import com.toxsl.volley.toolbox.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

/**
 * Created by TOXSL\himanshu.gulati on 12/6/18.
 */

public class TermsAndServicesFragment extends BaseFragment {
    private View view;
    private FragmentTermsBinding binding;
    Location current_location;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(
                    inflater, R.layout.fragment_terms, container, false);
            return binding.getRoot();
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        baseActivity.hideSoftKeyboard();
        ((LoginSignUpActivity) baseActivity).setToolbar(getString(R.string.terms_conditions), true);
        initUI();

    }

    private void initUI() {
        binding.goBT.setOnClickListener(this);
        current_location = new GoogleApisHandle().getLastKnownLocation(baseActivity);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goBT:
                if (store.getInt("role") == Const.ROLE_PROVIDER) {
                    if (validate()) {
                       // hitSignUpAPI();
                        doSignUpFromServer();
                    }
                  //  baseActivity.gotoOtpFragment();
                } else {
                    if (validate()) {
//                        hitCustomerSignUpAPI();
                        doSignUpFromServerCustomer();
                      //  baseActivity.gotoOtpFragment();
                    }
                }


                break;
        }
    }

    private boolean validate() {
        if (getArguments() == null) {
            showToast(baseActivity.getString(R.string.plz_try_again));
        } else if (!binding.termsCB.isChecked()) {
            showToast(baseActivity.getString(R.string.plz_acpt_terms));
        } else {
            return true;
        }
        return false;
    }

    private void hitSignUpAPI() {
        ProfileData profileData = getArguments().getParcelable("signupData");

        RequestParams requestParams = new RequestParams();
        requestParams.put("User[first_name]", profileData.first_name);
        requestParams.put("User[last_name]", profileData.last_name);
        requestParams.put("User[email]", profileData.email);
        requestParams.put("User[username]", profileData.username);
        requestParams.put("User[password]", profileData.password);
        requestParams.put("User[contact_no]", profileData.contact_no);
        requestParams.put("User[address]", profileData.address);
        requestParams.put("User[address_two]", profileData.address_two);
        requestParams.put("User[country]", profileData.country);
        requestParams.put("User[city]", profileData.city);
        requestParams.put("User[cartification_no]", profileData.certification); // param needed
        requestParams.put("User[city_state]", profileData.city_state);
        requestParams.put("User[availability_time_from]", profileData.availability_time_from);
        requestParams.put("User[availability_time_to]", profileData.availability_time_to);
        requestParams.put("User[zipcode]", profileData.zipcode);
        requestParams.put("User[gender]", profileData.gender);
        requestParams.put("File[multiImage]", profileData.multiImage);
        requestParams.put("User[education_level]", profileData.education_level);
        requestParams.put("UserCardDetail[card_holder_name]", profileData.cardHolderName);
        requestParams.put("UserCardDetail[card_number]", profileData.card_number);
        requestParams.put("UserCardDetail[cvv]", profileData.cvv);
        requestParams.put("CardDetail[type_id]", profileData.paymentType);
        requestParams.put("UserCardDetail[expiry_year]", profileData.expiry_year);
        requestParams.put("UserCardDetail[expiry_month]", profileData.expiry_month);
        requestParams.put("security_question", profileData.security_question);
        requestParams.put("sub_services", profileData.sub_services);


        baseActivity.syncManager.sendToServer(Const.API_USER_PROVIDER_SIGNUP, requestParams, this);
    }


    private void hitCustomerSignUpAPI() {
        ProfileData profileData = getArguments().getParcelable("signupData");


        RequestParams requestParams = new RequestParams();
        requestParams.put("User[first_name]", profileData.first_name);
        requestParams.put("User[last_name]", profileData.last_name);
        requestParams.put("User[email]", profileData.email);
        requestParams.put("User[username]", profileData.username);
        requestParams.put("User[password]", profileData.password);
        requestParams.put("User[contact_no]", profileData.contact_no);
        requestParams.put("User[address]", profileData.address);
        requestParams.put("User[address_two]", profileData.address_two);
        requestParams.put("User[country]", profileData.country);
        requestParams.put("User[city]", profileData.city);
        requestParams.put("User[cartification_no]", profileData.certification); // param needed
        requestParams.put("User[city_state]", profileData.city_state);
        requestParams.put("User[zipcode]", profileData.zipcode);
        requestParams.put("User[gender]", profileData.gender);
        requestParams.put("User[education_level]", profileData.education_level);
        requestParams.put("UserCardDetail[card_holder_name]", profileData.cardHolderName);
        requestParams.put("UserCardDetail[card_number]", profileData.card_number);
        requestParams.put("UserCardDetail[cvv]", profileData.cvv);
        requestParams.put("CardDetail[type_id]", profileData.paymentType);
        requestParams.put("UserCardDetail[expiry_year]", profileData.expiry_year);
        requestParams.put("UserCardDetail[expiry_month]", profileData.expiry_month);
        requestParams.put("security_question", profileData.security_question);

        baseActivity.syncManager.sendToServer(Const.API_USER_CUSTOMER_SIGNUP, requestParams, this);
    }

    @Override
    public void onSyncSuccess(String controller, String action, boolean status, JSONObject jsonObject) {
        super.onSyncSuccess(controller, action, status, jsonObject);
        try {
            if (jsonObject.getString("url").equalsIgnoreCase(Const.API_USER_PROVIDER_SIGNUP)) {
                if (jsonObject.getInt("status") == Const.STATUSOK) {
                    baseActivity.gotoOtpFragment();
                } else {
                    baseActivity.showToast(jsonObject.getString("error"));
                }

            } else if (jsonObject.getString("url").equalsIgnoreCase(Const.API_USER_CUSTOMER_SIGNUP)) {
                if (jsonObject.getInt("status") == Const.STATUSOK) {
                    baseActivity.gotoOtpFragment();
                } else {
                    baseActivity.showToast(jsonObject.getString("error"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void doSignUpFromServerCustomer() {

        final ACProgressFlower acProgressFlower = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();
        acProgressFlower.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, Const.NEW_BASE_URL
                + "customerSignup",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            RequestQueue queue = Volley.newRequestQueue(getActivity());
                            queue.getCache().clear();
                            Log.e("SignUpCustomerResponse==>>", response);
                            JSONObject json = new JSONObject(response);
                            String message = json.getString("message");

                            if (json.getString("status").equals("1")){

                                String id = json.getString("id");
                                String token = json.getString("token");

                                Const.saveData(getActivity(), "loginUserId", id);
                                Const.saveData(getActivity(), "loginUserToken", token);

                                baseActivity.gotoOtpFragment();

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

                /*firstname , lastname,email, password, device_type, device_token, username ,gender
                ,country_id, address, address2, state_id, city_id, latitude, longitude, zip_code,
                 mobile, security_que_ans, certificate_ids , name_of_card,  card_number,
                  card_exp_date, cvv*/

                ProfileData profileData = getArguments().getParcelable("signupData");
                String refreshedToken = baseActivity.getUniqueDeviceId();
                Log.e("SignUp", "refreshedToken==>> " + refreshedToken);

                Map<String, String> params = new HashMap<>();
                params.put("firstname", profileData.first_name);
                params.put("lastname", profileData.last_name);
                params.put("email", profileData.email);
                params.put("password", profileData.password);
                params.put("device_type", "1");
                params.put("device_token", refreshedToken);
                params.put("username", profileData.username);
                params.put("gender", String.valueOf(profileData.gender));
                params.put("country_id", String.valueOf(profileData.countryIds));
                params.put("address", profileData.address);
                params.put("address2", profileData.address_two);
                params.put("state_id", String.valueOf(profileData.stateIds));
                params.put("city_id", String.valueOf(profileData.cityIds));
                params.put("latitude", String.valueOf(current_location.getLatitude()));
                params.put("longitude", String.valueOf(current_location.getLongitude()));
                params.put("zip_code", profileData.zipcode);
                params.put("mobile", profileData.contact_no);
                params.put("security_que_ans", profileData.security_question.toString());
                params.put("certificate_ids", "");//profileData.multiImage
                params.put("education", profileData.education_level);
                params.put("name_of_card", profileData.cardHolderName);
                params.put("card_number", profileData.card_number);
                params.put("card_exp_date", profileData.expiry_month+"/"+profileData.expiry_year);
                params.put("cvv", profileData.cvv);

                Log.e("SignUpCustomer", "Params==>> " + params);

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


    public void doSignUpFromServer() {

        final ACProgressFlower acProgressFlower = new ACProgressFlower.Builder(getActivity())
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
               // .text("Title is here")
                .fadeColor(Color.DKGRAY).build();
        acProgressFlower.show();

        StringRequest postRequest = new StringRequest(Request.Method.POST, Const.NEW_BASE_URL
                + "serviceProviderSignup",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            RequestQueue queue = Volley.newRequestQueue(getActivity());
                            queue.getCache().clear();
                            Log.e("SignUpResponse==>>", response);
                            JSONObject json = new JSONObject(response);
                            String message = json.getString("message");

                            if (json.getString("status").equals("1")){

                                String id = json.getString("id");
                                String token = json.getString("token");
                                Const.saveData(getActivity(), "loginUserId", id);
                                Const.saveData(getActivity(), "loginUserToken", token);

                                baseActivity.gotoOtpFragment();

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

                /*firstname , lastname,email, password, device_type, device_token, username ,
                        gender('0', '1', '2')1-Male, 2-Female,0-Other,
                        country_id, country_id, address, address2, state_id, city_id, latitude, longitude,
                        zip_code, mobile, security_que_ans, certificate_ids,
                        sub_category(in josn value with 'sub_service_id' , 'charge_amount') ,  education,
                        name_of_card,  card_number, card_exp_date, cvv*/

                ProfileData profileData = getArguments().getParcelable("signupData");

                String refreshedToken = baseActivity.getUniqueDeviceId();
                Log.e("SignUp", "refreshedToken==>> " + refreshedToken);

                Map<String, String> params = new HashMap<>();
                params.put("firstname", profileData.first_name);
                params.put("lastname", profileData.last_name);
                params.put("email", profileData.email);
                params.put("password", profileData.password);
                params.put("device_type", "1");
                params.put("device_token", refreshedToken);
                params.put("username", profileData.username);
                params.put("gender", String.valueOf(profileData.gender));
                params.put("country_id", String.valueOf(profileData.countryIds));
                params.put("address", profileData.address);
                params.put("address2", profileData.address_two);
                params.put("state_id", String.valueOf(profileData.stateIds));
                params.put("city_id", String.valueOf(profileData.cityIds));
                params.put("latitude", String.valueOf(current_location.getLatitude()));
                params.put("longitude", String.valueOf(current_location.getLongitude()));
                params.put("zip_code", profileData.zipcode);
                params.put("mobile", profileData.contact_no);
                params.put("security_que_ans", profileData.security_question.toString());
                params.put("certificate_ids", profileData.selectedCertificateIds);//profileData.multiImage
                params.put("sub_category", profileData.sub_services.toString());
                params.put("education", profileData.education_level);
                params.put("name_of_card", profileData.cardHolderName);
                params.put("card_number", profileData.card_number);
                params.put("card_exp_date", profileData.expiry_month+"/"+profileData.expiry_year);
                params.put("cvv", profileData.cvv);
                params.put("start_time", profileData.availability_time_from);
                params.put("end_time", profileData.availability_time_to);
                params.put("description", profileData.description);
                params.put("license", profileData.certification);

                Log.e("SignUp", "Params==>> " + params);

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
