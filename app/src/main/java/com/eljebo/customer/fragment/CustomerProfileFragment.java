package com.eljebo.customer.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eljebo.R;
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.common.fragment.ChangePasswordFragment;
import com.eljebo.customer.activity.CustomerMainActivity;
import com.eljebo.databinding.FragmentCustomerProfileBinding;

/**
 * Created by TOXSL\vinay.goyal on 14/6/18.
 */

public class CustomerProfileFragment extends BaseFragment {

    private View view;
    private FragmentCustomerProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_customer_profile, container, false);
            return binding.getRoot();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;
        baseActivity.hideSoftKeyboard();
        ((CustomerMainActivity) baseActivity).setTitle(getString(R.string.profile));
        init();
    }

    private void init() {
        binding.changePasswordET.setOnClickListener(this);
        binding.payBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {

            case R.id.changePasswordET:
                baseActivity.hideSoftKeyboard();

                gotoChangePasswordFragment();
                break;
            case R.id.payBT:
                baseActivity.hideSoftKeyboard();

                break;
        }
    }

    private void gotoChangePasswordFragment() {
        baseActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.customer_container, new ChangePasswordFragment())
                .addToBackStack(null)
                .commit();
    }
}
