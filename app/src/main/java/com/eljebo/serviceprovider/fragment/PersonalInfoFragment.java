package com.eljebo.serviceprovider.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eljebo.R;
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.databinding.FragmentPersonalInfoBinding;
import com.eljebo.serviceprovider.activity.MainActivity;

/**
 * Created by TOXSL\himanshu.gulati on 11/6/18.
 */

public class PersonalInfoFragment extends BaseFragment {
    private View view;
    private FragmentPersonalInfoBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MainActivity)getActivity()).setTitle(getString(R.string.personal_information));
        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(
                    inflater, R.layout.fragment_personal_info, container, false);
            return binding.getRoot();
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        initUI();

    }

    private void initUI() {
        binding.saveBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveBT:
                baseActivity.hideSoftKeyboard();
                break;
        }
    }
}
