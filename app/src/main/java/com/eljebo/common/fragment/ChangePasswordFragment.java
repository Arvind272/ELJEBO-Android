package com.eljebo.common.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.eljebo.R;
import com.eljebo.common.utils.Const;
import com.eljebo.customer.activity.CustomerMainActivity;
import com.eljebo.databinding.FragmentChangePasswordBinding;
import com.eljebo.serviceprovider.activity.MainActivity;

/**
 * Created by TOXSL\himanshu.gulati on 11/6/18.
 */

public class ChangePasswordFragment extends BaseFragment {
    private View view;
    private FragmentChangePasswordBinding binding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil
                    .inflate(inflater, R.layout.fragment_change_password, container, false);
            return binding.getRoot();
        }
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        baseActivity.hideSoftKeyboard();
        if (store.getInt("role") == Const.ROLE_USER) {
            ((CustomerMainActivity) baseActivity).setTitle(getString(R.string.change_password));
        } else {
            ((MainActivity) baseActivity).setTitle(getString(R.string.change_password));
        }
        initUI();
    }

    private void initUI() {
        binding.confirmBT.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirmBT:
                baseActivity.hideSoftKeyboard();

                break;
        }
    }
}
