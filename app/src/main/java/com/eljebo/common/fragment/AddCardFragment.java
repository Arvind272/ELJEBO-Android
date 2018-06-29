package com.eljebo.common.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eljebo.R;
import com.eljebo.common.activity.LoginSignUpActivity;
import com.eljebo.common.data.ProfileData;
import com.eljebo.databinding.FragmentAddCardBinding;

/**
 * Created by TOXSL\himanshu.gulati on 12/6/18.
 */

public class AddCardFragment extends BaseFragment {
    private View view;
    private FragmentAddCardBinding binding;
    private int payment_type = 0;
    private boolean isValid;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(
                    inflater, R.layout.fragment_add_card, container, false);
            return binding.getRoot();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        baseActivity.hideSoftKeyboard();
        ((LoginSignUpActivity) baseActivity).setToolbar(getString(R.string.add_card), true);
        initUI();

    }

    private void initUI() {

        Bundle bundle = getArguments();
        if (bundle != null) {
            payment_type = bundle.getInt("payment_type");
        }
        binding.saveBT.setOnClickListener(this);

        binding.mmYYET.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String working = s.toString();
                isValid = true;
                if (working.length() == 2 && before == 0) {
                    if (Integer.parseInt(working) < 1 || Integer.parseInt(working) > 12) {
                        isValid = false;
                    } else {
                        working += "/";
                        binding.mmYYET.setText(working);
                        binding.mmYYET.setSelection(working.length());
                    }
                }
                if (working.length() > 2 && !working.contains("/")) {
                    isValid = false;
                }

                if (!isValid) {
                    binding.mmYYET.setError("Enter a valid date: MM/YY");
                } else {
                    binding.mmYYET.setError(null);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveBT:
                baseActivity.hideSoftKeyboard();
                if (validation()) {
                    gotoTermsFragment();
                }
                break;
        }
    }

    private void gotoTermsFragment() {
        Fragment fragment = new TermsAndServicesFragment();
        ProfileData profileData = getArguments().getParcelable("signupData");
        profileData.cardHolderName = binding.nameOnCardET.getText().toString().trim();
        profileData.card_number = binding.cardNumberET.getText().toString().trim();
        profileData.cvv = binding.cVVET.getText().toString().trim();
        profileData.expiry_year = binding.mmYYET.getText().toString().trim().substring(3, 5);
        profileData.expiry_month = binding.mmYYET.getText().toString().trim().substring(0, 2);
        profileData.paymentType = getArguments().getInt("payment_type");
        Bundle bundle = new Bundle();
        bundle.putParcelable("signupData", profileData);
        fragment.setArguments(bundle);
        baseActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_frame, fragment)
                .addToBackStack(null)
                .commit();
    }

    public boolean validation() {
        if (getArguments() == null || !getArguments().containsKey("signupData")) {
            showToast(baseActivity.getString(R.string.plz_try_again));
        } else if (binding.nameOnCardET.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.enter_name));
        } else if (binding.cardNumberET.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.enter_card_number));
        } else if (binding.cardNumberET.getText().toString().length() < 16) {
            showToast(baseActivity.getString(R.string.enter_valid_card_number));
        } else if (binding.mmYYET.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.enter_expiry_date));
        } else if (!isValid) {
            showToastOne(getString(R.string.enter_month_year));
        } else if (binding.mmYYET.getText().toString().trim().length() < 5) {
            showToastOne(getString(R.string.enter_month_year));
        } else if (binding.cVVET.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.add_cvv));
        } else if (binding.cVVET.getText().toString().trim().length() > 4 || binding.cVVET.getText().toString().trim().length() < 3) {
            showToastOne(getString(R.string.pls_enter_3_4_digits_cvv));
        } else {
            return true;
        }
        return false;
    }
}

