package com.eljebo.common.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eljebo.R;
import com.eljebo.common.utils.Const;
import com.eljebo.customer.activity.CustomerMainActivity;
import com.eljebo.customer.fragment.CustomerHomeFragment;
import com.eljebo.databinding.FragmentFeedbackBinding;
import com.eljebo.serviceprovider.activity.MainActivity;
import com.eljebo.serviceprovider.fragment.HomeFragment;

/**
 * Created by TOXSL\vinay.goyal on 12/6/18.
 */

public class FeedbackFragment extends BaseFragment {

    private View view;
    private FragmentFeedbackBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feedback, container, false);
            return binding.getRoot();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;
        baseActivity.hideSoftKeyboard();
        if (store.getInt("role") == Const.ROLE_USER) {
            binding.dollarTV.setVisibility(View.VISIBLE);
            ((CustomerMainActivity) baseActivity).setTitle(getString(R.string.feedback));
        } else {
            binding.dollarTV.setVisibility(View.GONE);
            ((MainActivity) baseActivity).setTitle(getString(R.string.feedback));
        }
        initUI();
    }

    private void initUI() {
        binding.submitBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitBT:
                baseActivity.getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                if (store.getInt("role") == Const.ROLE_USER) {
                    baseActivity.getSupportFragmentManager().beginTransaction().replace(R.id.customer_container, new CustomerHomeFragment()).commit();
                } else {
                    baseActivity.getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
                }
                break;
        }
    }
}
