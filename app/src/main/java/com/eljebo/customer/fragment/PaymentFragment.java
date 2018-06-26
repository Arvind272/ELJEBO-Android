package com.eljebo.customer.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eljebo.R;
import com.eljebo.common.activity.LoginSignUpActivity;
import com.eljebo.common.fragment.AddCardFragment;
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.common.utils.Const;
import com.eljebo.databinding.FragmentPaymentBinding;


/**
 * Created by TOXSL\vinay.goyal on 13/6/18.
 */

public class PaymentFragment extends BaseFragment {

    private View view;
    private FragmentPaymentBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment, container, false);
            return binding.getRoot();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;
        baseActivity.hideSoftKeyboard();
        ((LoginSignUpActivity) baseActivity).setToolbar(getString(R.string.payments), true);
        initUI();
    }

    private void initUI() {

        binding.creditCardTV.setOnClickListener(this);
        binding.debitCardTV.setOnClickListener(this);
        binding.atmCardTV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {

            case R.id.creditCardTV:
                gotoAddCardFragment(Const.CREDIT_CARD);
                break;

            case R.id.debitCardTV:
                gotoAddCardFragment(Const.DEBIT_CARD);
                break;

            case R.id.atmCardTV:
                gotoAddCardFragment(Const.ATM_CARD);
                break;

            case R.id.paypalTV:
                gotoAddCardFragment(Const.PAYPAL);
                break;
        }
    }

    private void gotoAddCardFragment(int payment_type) {

        Fragment fragment = new AddCardFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("payment_type", payment_type);
        if (getArguments() != null) {
            bundle.putParcelable("signupData", getArguments().getParcelable("signupData"));
        }
        fragment.setArguments(bundle);
        baseActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.login_frame, fragment)
                .addToBackStack(null)
                .commit();
    }
}
