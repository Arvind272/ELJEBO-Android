package com.eljebo.serviceprovider.fragment;

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
import com.eljebo.databinding.FragmentServiceUserDetailBinding;
import com.eljebo.serviceprovider.activity.MainActivity;

/**
 * Created by TOXSL\vinay.goyal on 12/6/18.
 */

public class ServiceUserDetailFragment extends BaseFragment {

    private View view;
    private FragmentServiceUserDetailBinding binding;
    private ServiceAdapter serviceAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_service_user_detail, container, false);
            return binding.getRoot();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((MainActivity) baseActivity).setTitle(getString(R.string.jack_thomas));
        this.view = view;
        baseActivity.hideSoftKeyboard();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.questionsLL.servicesRV.setLayoutManager(linearLayoutManager);

        if (baseActivity.store.containValue("selectedServices")) {
            serviceAdapter = new ServiceAdapter(this, baseActivity.store.<SubService>getData("selectedServices"));
            binding.questionsLL.servicesRV.setAdapter(serviceAdapter);
        } else {
            binding.questionsLL.cleanerLL.setVisibility(View.VISIBLE);
        }
        initUI();
    }

    private void initUI() {

        binding.questionsLL.serviceLL.setVisibility(View.VISIBLE);
        binding.checkInCTV.setOnClickListener(this);
        binding.timerOnIV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {

            case R.id.checkInCTV:
                gotoCheckInFragment();
                break;

            case R.id.timerOnIV:
                gotoCheckInFragment();
                break;
        }
    }

    private void gotoCheckInFragment() {

        baseActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new CheckInFragment())
                .addToBackStack(null)
                .commit();
    }
}
