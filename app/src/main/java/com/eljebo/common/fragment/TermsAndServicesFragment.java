package com.eljebo.common.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eljebo.R;
import com.eljebo.common.activity.LoginSignUpActivity;
import com.eljebo.common.data.ProfileData;
import com.eljebo.common.utils.Const;
import com.eljebo.databinding.FragmentTermsBinding;
import com.toxsl.volley.toolbox.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by TOXSL\himanshu.gulati on 12/6/18.
 */

public class TermsAndServicesFragment extends BaseFragment {
    private View view;
    private FragmentTermsBinding binding;

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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goBT:
                if (store.getInt("role") == Const.ROLE_PROVIDER) {
//                    if (validate()) {
//                        hitSignUpAPI();
//                    }
                    baseActivity.gotoOtpFragment();
                } else {
//                    if (validate()) {
//                        hitCustomerSignUpAPI();
                        baseActivity.gotoOtpFragment();
//                    }
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

}
