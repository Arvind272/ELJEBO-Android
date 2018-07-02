package com.eljebo.customer.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eljebo.R;
import com.eljebo.common.adapter.ServiceAdapter;
import com.eljebo.common.data.SubService;
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.customer.activity.CustomerMainActivity;
import com.eljebo.databinding.FragmentPayBinding;

/**
 * Created by TOXSL\vinay.goyal on 18/6/18.
 */

public class PayFragment extends BaseFragment {

    private FragmentPayBinding binding;
    private View view;
    private Bundle bundle;
    private ServiceAdapter serviceAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pay, container, false);
            return binding.getRoot();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((CustomerMainActivity) baseActivity).setTitle(getString(R.string.jack_thomas));
        this.view = view;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.questionsLL.servicesRV.setLayoutManager(linearLayoutManager);

        if (baseActivity.store.containValue("selectedServices")) {
            serviceAdapter = new ServiceAdapter(this, baseActivity.store.<SubService>getData("selectedServices"));
            binding.questionsLL.servicesRV.setAdapter(serviceAdapter);
        } else {
            binding.questionsLL.cleanerLL.setVisibility(View.VISIBLE);
        }
        baseActivity.hideSoftKeyboard();
        binding.questionsLL.availabilityLL.setVisibility(View.VISIBLE);
        init();
    }

    private void init() {

        bundle = getArguments();
        if (bundle != null) {
            if (!bundle.getString("date").isEmpty()) {
                binding.questionsLL.bookingDateTV.setText(bundle.getString("date"));
            } else {
                binding.questionsLL.bookingDateTV.setText(getString(R.string.wednesday_23rd_june_2018));
            }
            if (!bundle.getString("time").isEmpty()) {
                binding.questionsLL.bookingTimeTV.setText(bundle.getString("time"));
            } else {
                binding.questionsLL.bookingTimeTV.setText("2:00");
            }
            if (!bundle.getString("duration").isEmpty()) {
                if (bundle.getString("duration").equals("1")) {
                    binding.questionsLL.durationTV.setText(bundle.getString("duration") + " min");
                } else {
                    binding.questionsLL.durationTV.setText(bundle.getString("duration") + " mins");
                }

            } else {
                binding.questionsLL.durationTV.setText(getString(R.string._1_mins));
            }
        }
        binding.questionsLL.educationLL.setVisibility(View.VISIBLE);
        binding.questionsLL.availabilityLL.setVisibility(View.VISIBLE);
        binding.questionsLL.bookingDetailLL.setVisibility(View.VISIBLE);
        binding.payBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {

            case R.id.payBT:
                gotoServiceProviderDetailFragment();
                break;
        }
    }

    private void gotoServiceProviderDetailFragment() {
        ServiceProviderDetailFragment serviceProviderDetailFragment =
                new ServiceProviderDetailFragment();
        serviceProviderDetailFragment.setArguments(bundle);
        baseActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.customer_container, serviceProviderDetailFragment)
                .addToBackStack(null)
                .commit();
    }
}
