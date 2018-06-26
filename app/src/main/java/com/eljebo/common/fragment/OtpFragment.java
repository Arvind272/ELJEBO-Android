package com.eljebo.common.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eljebo.R;
import com.eljebo.common.activity.LoginSignUpActivity;
import com.eljebo.common.utils.Const;
import com.eljebo.databinding.FragmentOtpBinding;

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
//                validate();
                sendDataToServer();
                break;
        }
    }

    private void validate() {
        if (binding.codeET.getText().toString().trim().isEmpty()) {
            showToastOne(baseActivity.getString(R.string.enter_6_digit_otp_code_sent_to_your_email));
        } else {
            sendDataToServer();
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
}
