package com.eljebo.customer.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eljebo.R;
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.common.fragment.FeedbackFragment;
import com.eljebo.customer.activity.CustomerMainActivity;
import com.eljebo.databinding.FragmentCustomerCheckInTimerBinding;

/**
 * Created by TOXSL\vinay.goyal on 14/6/18.
 */

public class CustomerCheckInTimerFragment extends BaseFragment {

    private View view;
    private FragmentCustomerCheckInTimerBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_customer_check_in_timer, container, false);
            return binding.getRoot();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;
        baseActivity.hideSoftKeyboard();
        ((CustomerMainActivity) baseActivity).setTitle(getString(R.string._check_in_timer));

        init();
    }

    private void init() {
        String s = "20mins";
        SpannableString ss1 = new SpannableString(s);
        ss1.setSpan(new RelativeSizeSpan(3f), 0, 2, 0); // set size
        ss1.setSpan(new RelativeSizeSpan(1.5f), 2, 6, 0); // set size
        ss1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 0, 2, 0);// set color
        binding.minutesTV.setText(ss1);
        binding.payBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {

            case R.id.payBT:
                gotoFeedbackFragment();
                break;
        }
    }

    private void gotoFeedbackFragment() {
        baseActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.customer_container, new FeedbackFragment())
                .addToBackStack(null)
                .commit();
    }
}
