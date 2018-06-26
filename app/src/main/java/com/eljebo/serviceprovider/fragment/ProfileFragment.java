package com.eljebo.serviceprovider.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eljebo.R;
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.common.fragment.ChangePasswordFragment;
import com.eljebo.databinding.FragmentProfileBinding;
import com.eljebo.serviceprovider.activity.MainActivity;

/**
 * Created by TOXSL\himanshu.gulati on 12/6/18.
 */

public class ProfileFragment extends BaseFragment {
    private View view;
    private FragmentProfileBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((MainActivity) getActivity()).setTitle(getString(R.string.profile));
        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(
                    inflater, R.layout.fragment_profile, container, false);
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
        binding.personalInfoTV.setOnClickListener(this);
        binding.changePasswordTV.setOnClickListener(this);
        binding.servicesTV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Fragment frag = null;
        switch (v.getId()) {
            case R.id.personalInfoTV:
                frag = new PersonalInfoFragment();
                break;
            case R.id.changePasswordTV:
                frag = new ChangePasswordFragment();
                break;
            case R.id.servicesTV:
                frag = new ServicesFragment();
                break;
        }

        if (frag != null) {
            baseActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, frag)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
