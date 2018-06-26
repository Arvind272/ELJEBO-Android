package com.eljebo.serviceprovider.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.eljebo.common.fragment.FeedbackFragment;
import com.eljebo.databinding.FragmentCheckInBinding;
import com.eljebo.serviceprovider.activity.MainActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Queue;

/**
 * Created by TOXSL\vinay.goyal on 12/6/18.
 */

public class CheckInFragment extends BaseFragment {

    Handler handler = new Handler();
    Object object = new Object();
    private View view;
    private CountDownTimer cTimer;
    private FragmentCheckInBinding binding;
    private ServiceAdapter serviceAdapter;
    private int MM = 0, SS = 0, HH = 0;
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
            if (MM >= 1 && SS > 0) {
                binding.extraTimeTV.setVisibility(View.VISIBLE);
            }
            NumberFormat f = new DecimalFormat("00");
            s = f.format(SS);
            m = f.format(MM);
            h = f.format(HH);
            binding.timerCTV.setText(h + ":" + m + ":" + s);
            handler.postDelayed(this, 1000);
        }
    };
    private Queue q;
    private Object lock = new Object();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_check_in, container, false);
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
        ((MainActivity) baseActivity).setTitle(getString(R.string._check_in_timer));
    }

    private void initUI() {
        if (handler != null) {
            handler.postDelayed(r, 1000);
        }
        baseActivity.hideSoftKeyboard();
        binding.questionsLL.durationLL.setVisibility(View.GONE);
        binding.questionsLL.serviceLL.setVisibility(View.VISIBLE);
        binding.timerOffBT.setOnClickListener(this);
        binding.timerOnBT.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {

            case R.id.timerOffBT:
                baseActivity.hideSoftKeyboard();
                if (handler != null) {
                    handler.removeCallbacks(r);
                }
                gotoFeedbackFragment();
                break;

            case R.id.timerOnBT:
                baseActivity.hideSoftKeyboard();
                binding.timerOnBT.setVisibility(View.GONE);
                binding.timerOffBT.setVisibility(View.VISIBLE);
                if (handler != null) {
                    handler.postDelayed(r, 1000);
                }
                break;
        }

    }

    private void gotoFeedbackFragment() {
        baseActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new FeedbackFragment())
                .addToBackStack(null)
                .commit();
    }


}
