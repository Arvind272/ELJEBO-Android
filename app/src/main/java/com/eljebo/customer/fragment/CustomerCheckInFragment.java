package com.eljebo.customer.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
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
import com.eljebo.databinding.FragmentCustomerCheckInBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by TOXSL\vinay.goyal on 14/6/18.
 */

public class CustomerCheckInFragment extends BaseFragment {

    Handler handler = new Handler();
    private View view;
    private FragmentCustomerCheckInBinding binding;
    private int MM = 0, SS = 0, HH = 0;
    private Bundle bundle;
    private int duration;
    Runnable r = new Runnable() {
        @Override
        public void run() {
            String s, m, h;
            SS++;
            if (SS == 60) {
                MM++;
                SS = 0;
            }

            if (MM == 60) {
                HH++;
                MM = 0;
            }
            bundle = getArguments();
            if (bundle != null) {
                if (!bundle.getString("duration").isEmpty()) {
                    duration = Integer.parseInt(bundle.getString("duration"));
                }
            }
            if (duration != 0) {
                int hour = duration / 60;
                int min = duration % 60;
                if (HH >= hour && MM >= min && SS > 0) {
                    binding.extraTimeTV.setVisibility(View.VISIBLE);
                }
            } else {
                if (MM >= 1 && SS > 0) {
                    binding.extraTimeTV.setVisibility(View.VISIBLE);
                }
            }

            NumberFormat f = new DecimalFormat("00");
            s = f.format(SS);
            m = f.format(MM);
            h = f.format(HH);
            binding.timerCTV.setText(h + ":" + m + ":" + s);
            handler.postDelayed(this, 1000);
        }
    };
    private ServiceAdapter serviceAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_customer_check_in, container, false);
            initUI();
            return binding.getRoot();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
        ((CustomerMainActivity) baseActivity).setTitle(getString(R.string._check_in_timer));
    }

    private void initUI() {

        if (handler != null) {
            handler.postDelayed(r, 1000);
        }
        bundle = getArguments();
        if (!bundle.getString("duration").isEmpty()) {
            if (bundle.getString("duration").equals("1")) {
                binding.questionsLL.duration.setText(bundle.getString("duration") + "min");
            } else {
                binding.questionsLL.duration.setText(bundle.getString("duration") + "mins");
            }
        } else {
            binding.questionsLL.duration.setText(getString(R.string._1_mins));
        }

        binding.extraTimeTV.setTextColor(getResources().getColor(R.color.colorPrimary));
        binding.questionsLL.educationLL.setVisibility(View.VISIBLE);
        binding.questionsLL.availabilityLL.setVisibility(View.VISIBLE);
        binding.questionsLL.durationLL.setVisibility(View.VISIBLE);
        binding.questionsLL.certificationLL.setVisibility(View.VISIBLE);
        binding.timerOffIV.setOnClickListener(this);
        binding.timerOnIV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {

            case R.id.timerOffIV:
                baseActivity.hideSoftKeyboard();

                if (handler != null) {
                    handler.removeCallbacks(r);
                }
                gotoCheckInTimerFragment();
                break;

            case R.id.timerOnIV:
                baseActivity.hideSoftKeyboard();
                binding.timerOnIV.setVisibility(View.GONE);
                binding.timerOffIV.setVisibility(View.VISIBLE);
                if (handler != null) {
                    handler.postDelayed(r, 1000);
                }
                break;

        }
    }

    private void gotoCheckInTimerFragment() {
        baseActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.customer_container, new CustomerCheckInTimerFragment())
                .addToBackStack(null)
                .commit();
    }

}

