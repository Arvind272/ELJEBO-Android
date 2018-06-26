package com.eljebo.common.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eljebo.R;
import com.eljebo.databinding.FragmentForgotPasswordBinding;


/**
 * Created by TOXSL\chirag.tyagi on 27/2/18.
 */

public class ForgotPasswordFragment extends BaseFragment {
    private View view;
    private FragmentForgotPasswordBinding binding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil
                    .inflate(inflater, R.layout.fragment_forgot_password, container, false);
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

        binding.sendBT.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.sendBT:
                baseActivity.hideSoftKeyboard();

                loadOtpFragment();
                break;
        }
    }

    private void loadOtpFragment() {

        Fragment fragment = new OtpFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("key", true);
        fragment.setArguments(bundle);
        baseActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.login_frame, fragment)
                .addToBackStack(null)
                .commit();
    }


}
