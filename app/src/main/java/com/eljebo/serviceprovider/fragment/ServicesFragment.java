package com.eljebo.serviceprovider.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.eljebo.R;
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.common.utils.CustomTextView;
import com.eljebo.databinding.FragmentServicesBinding;
import com.eljebo.serviceprovider.activity.MainActivity;

/**
 * Created by TOXSL\himanshu.gulati on 12/6/18.
 */

public class ServicesFragment extends BaseFragment {
    private View view;
    private FragmentServicesBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setTitle(getString(R.string.services));
        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(
                    inflater, R.layout.fragment_services, container, false);
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
        binding.servicesLL.serviceSV.setVisibility(View.VISIBLE);
        binding.servicesLL.holdAidServicesTV.setOnClickListener(this);
        binding.servicesLL.decorationTV.setOnClickListener(this);
        binding.servicesLL.plannersTV.setOnClickListener(this);
        binding.servicesLL.technicalTV.setOnClickListener(this);
        binding.servicesLL.cateringTV.setOnClickListener(this);
        binding.servicesLL.educationTV.setOnClickListener(this);
        binding.servicesLL.homeServiceLL.setVisibility(View.GONE);
        binding.servicesLL.holdAidServicesTV.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_dwnarw, 0);
        binding.servicesLL.holdAidServicesTV.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.holdAidServicesTV:
                if (binding.servicesLL.homeServiceLL.getVisibility() == View.GONE) {
                    openSelected(binding.servicesLL.homeServiceLL, binding.servicesLL.cateringServicesLL, binding.servicesLL.educationServicesLL
                            , binding.servicesLL.decorationServicesLL, binding.servicesLL.plannersServicesLL, binding.servicesLL.technicalServicesLL);

                    changeArrow(binding.servicesLL.holdAidServicesTV, binding.servicesLL.cateringTV, binding.servicesLL.educationTV
                            , binding.servicesLL.decorationTV, binding.servicesLL.plannersTV, binding.servicesLL.technicalTV);
                } else {
                    binding.servicesLL.homeServiceLL.setVisibility(View.GONE);
                    binding.servicesLL.holdAidServicesTV.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_dwnarw, 0);
                }

                break;

            case R.id.cateringTV:
                if (binding.servicesLL.cateringServicesLL.getVisibility() == View.GONE) {
                    openSelected(binding.servicesLL.cateringServicesLL, binding.servicesLL.homeServiceLL, binding.servicesLL.educationServicesLL
                            , binding.servicesLL.decorationServicesLL, binding.servicesLL.plannersServicesLL, binding.servicesLL.technicalServicesLL);

                    changeArrow(binding.servicesLL.cateringTV, binding.servicesLL.holdAidServicesTV, binding.servicesLL.educationTV
                            , binding.servicesLL.decorationTV, binding.servicesLL.plannersTV, binding.servicesLL.technicalTV);
                } else {
                    binding.servicesLL.cateringServicesLL.setVisibility(View.GONE);
                    binding.servicesLL.cateringTV.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_dwnarw, 0);
                }
                break;

            case R.id.educationTV:
                if (binding.servicesLL.educationServicesLL.getVisibility() == View.GONE) {
                    openSelected(binding.servicesLL.educationServicesLL, binding.servicesLL.homeServiceLL, binding.servicesLL.cateringServicesLL
                            , binding.servicesLL.decorationServicesLL, binding.servicesLL.plannersServicesLL, binding.servicesLL.technicalServicesLL);

                    changeArrow(binding.servicesLL.educationTV, binding.servicesLL.holdAidServicesTV, binding.servicesLL.cateringTV
                            , binding.servicesLL.decorationTV, binding.servicesLL.plannersTV, binding.servicesLL.technicalTV);
                } else {
                    binding.servicesLL.educationServicesLL.setVisibility(View.GONE);
                    binding.servicesLL.educationTV.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_dwnarw, 0);
                }
                break;

            case R.id.decorationTV:
                if (binding.servicesLL.decorationServicesLL.getVisibility() == View.GONE) {
                    openSelected(binding.servicesLL.decorationServicesLL, binding.servicesLL.homeServiceLL, binding.servicesLL.cateringServicesLL
                            , binding.servicesLL.educationServicesLL, binding.servicesLL.plannersServicesLL, binding.servicesLL.technicalServicesLL);

                    changeArrow(binding.servicesLL.decorationTV, binding.servicesLL.holdAidServicesTV, binding.servicesLL.cateringTV
                            , binding.servicesLL.educationTV, binding.servicesLL.plannersTV, binding.servicesLL.technicalTV);
                } else {
                    binding.servicesLL.decorationServicesLL.setVisibility(View.GONE);
                    binding.servicesLL.decorationTV.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_dwnarw, 0);
                }
                break;

            case R.id.plannersTV:
                if (binding.servicesLL.plannersServicesLL.getVisibility() == View.GONE) {
                    openSelected(binding.servicesLL.plannersServicesLL, binding.servicesLL.homeServiceLL, binding.servicesLL.cateringServicesLL
                            , binding.servicesLL.educationServicesLL, binding.servicesLL.decorationServicesLL, binding.servicesLL.technicalServicesLL);

                    changeArrow(binding.servicesLL.plannersTV, binding.servicesLL.holdAidServicesTV, binding.servicesLL.cateringTV
                            , binding.servicesLL.educationTV, binding.servicesLL.decorationTV, binding.servicesLL.technicalTV);
                } else {
                    binding.servicesLL.plannersServicesLL.setVisibility(View.GONE);
                    binding.servicesLL.plannersTV.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_dwnarw, 0);
                }
                break;

            case R.id.technicalTV:
                if (binding.servicesLL.technicalServicesLL.getVisibility() == View.GONE) {
                    openSelected(binding.servicesLL.technicalServicesLL, binding.servicesLL.homeServiceLL, binding.servicesLL.cateringServicesLL
                            , binding.servicesLL.educationServicesLL, binding.servicesLL.decorationServicesLL, binding.servicesLL.plannersServicesLL);

                    changeArrow(binding.servicesLL.technicalTV, binding.servicesLL.holdAidServicesTV, binding.servicesLL.cateringTV
                            , binding.servicesLL.educationTV, binding.servicesLL.decorationTV, binding.servicesLL.plannersTV);
                } else {
                    binding.servicesLL.technicalServicesLL.setVisibility(View.GONE);
                    binding.servicesLL.technicalTV.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_dwnarw, 0);
                }
                break;
        }
    }


    private void changeArrow(CustomTextView selected, CustomTextView unselected, CustomTextView unselected1, CustomTextView unselected2, CustomTextView unselected3, CustomTextView unselected4) {
        selected.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_uparw, 0);
        unselected.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_dwnarw, 0);
        unselected1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_dwnarw, 0);
        unselected2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_dwnarw, 0);
        unselected3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_dwnarw, 0);
        unselected4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_dwnarw, 0);


    }

    private void openSelected(LinearLayout showLayout, LinearLayout hidelayout, LinearLayout hidelayout1, LinearLayout hidelayout2, LinearLayout hidelayout3, LinearLayout hidelayout4) {
        showLayout.setVisibility(View.VISIBLE);
        hidelayout.setVisibility(View.GONE);
        hidelayout1.setVisibility(View.GONE);
        hidelayout2.setVisibility(View.GONE);
        hidelayout3.setVisibility(View.GONE);
        hidelayout4.setVisibility(View.GONE);
    }
}

