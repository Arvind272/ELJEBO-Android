package com.eljebo.common.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eljebo.R;
import com.eljebo.common.activity.LoginSignUpActivity;
import com.eljebo.common.utils.Const;
import com.eljebo.databinding.FragmentSelectRoleBinding;

/**
 * Created by TOXSL\himanshu.gulati on 11/6/18.
 */

public class RoleSelectionFragment extends BaseFragment {
    private View view;
    private FragmentSelectRoleBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(
                    inflater, R.layout.fragment_select_role, container, false);
            return binding.getRoot();
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        baseActivity.hideSoftKeyboard();
        ((LoginSignUpActivity) baseActivity).setToolbar("", false);
        initUI();
    }

    private void initUI() {
        String text = getString(R.string.welcome_to) + " <font color=#F81617>Eljebo!</font>";
        binding.welcomeTV.setText(Html.fromHtml(text));
        binding.userBT.setOnClickListener(this);
        binding.serviceProviderBT.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.userBT:
                gotoLoginFragment(Const.ROLE_USER);
                break;
            case R.id.serviceProviderBT:
                gotoLoginFragment(Const.ROLE_PROVIDER);
                break;
        }
    }


    private void gotoLoginFragment(int role) {
        Fragment fragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("role", role);
        store.setInt("role", role);
        fragment.setArguments(bundle);
        baseActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_frame, fragment)
                .addToBackStack(null)
                .commit();

    }


}
