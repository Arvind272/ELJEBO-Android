package com.eljebo.customer.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.eljebo.R;
import com.eljebo.common.activity.BaseActivity;
import com.eljebo.common.activity.LoginSignUpActivity;
import com.eljebo.common.data.ProfileData;
import com.eljebo.common.dialog.CountryDialog;
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.common.utils.Const;
import com.eljebo.databinding.FragmentCustomerSignupBinding;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by TOXSL\vinay.goyal on 12/6/18.
 */

public class CustomerSignUpFragment extends BaseFragment {
    private View view;
    private FragmentCustomerSignupBinding binding;
    private int cityID, countryID, stateID;
    private int gender = 0;
    private int type = 0;
    private Dialog countryDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(
                    inflater, R.layout.fragment_customer_signup, container, false);
            return binding.getRoot();
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        baseActivity.hideSoftKeyboard();
        ((LoginSignUpActivity) baseActivity).setToolbar("", false);
        binding.firstNameET.setFilters(new InputFilter[]{baseActivity.getEditTextFilter()});
        binding.lastNameET.setFilters(new InputFilter[]{baseActivity.getEditTextFilter()});

        initUI();

    }

    private void initUI() {

        binding.maleRB.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/unicodearialr.ttf"));
        binding.femaleRB.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/unicodearialr.ttf"));
        binding.otherRB.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/unicodearialr.ttf"));

        binding.nextBT.setOnClickListener(this);
        binding.maleRB.setOnCheckedChangeListener(this);
        binding.femaleRB.setOnCheckedChangeListener(this);
        binding.otherRB.setOnCheckedChangeListener(this);

        binding.cityET.setOnClickListener(this);
        binding.stateET.setOnClickListener(this);
        binding.countryET.setOnClickListener(this);
        binding.addressET.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextBT:
                baseActivity.hideSoftKeyboard();
                if (validate()) {
                gotoPaymentFragment();
                }
                break;
            case R.id.cityET:
                baseActivity.hideSoftKeyboard();
                if (countryID == 0 || stateID == 0) {
                    showToast(getString(R.string.select_state));
                } else {
                    type = Const.CITY;
                    countryDialog = new CountryDialog(baseActivity, stateID, type, this);
                    countryDialog.show();
                }
                break;
            case R.id.countryET:
                baseActivity.hideSoftKeyboard();
                type = Const.COUNTRY;
                countryDialog = new CountryDialog(baseActivity, countryID, type, this);
                countryDialog.show();

                break;
            case R.id.stateET:
                if (countryID == 0) {
                    showToast(getString(R.string.select_country));
                } else {
                    type = Const.STATE;

                    countryDialog = new CountryDialog(baseActivity, countryID, type, this);
                    countryDialog.show();
                    baseActivity.hideSoftKeyboard();
                }
                break;

            case R.id.addressET:
                callGoogleSearch(Const.LOCATION_CODE);
                break;
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        super.onCheckedChanged(buttonView, isChecked);

        switch (buttonView.getId()) {

            case R.id.maleRB:
                if (isChecked)
                    changeGender(binding.maleRB, binding.femaleRB, binding.otherRB);
                break;

            case R.id.femaleRB:
                if (isChecked)
                    changeGender(binding.femaleRB, binding.maleRB, binding.otherRB);
                break;

            case R.id.otherRB:
                if (isChecked)
                    changeGender(binding.otherRB, binding.femaleRB, binding.maleRB);
                break;
        }
    }

    private void changeGender(RadioButton selected, RadioButton unselected, RadioButton unselected1) {
        selected.setChecked(true);
        unselected.setChecked(false);
        unselected1.setChecked(false);
    }

    private boolean validate() {

        if (binding.firstNameET.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.enter_first_name));
        } else if (binding.lastNameET.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.enter_last_name));
        } else if (binding.emailET.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.please_enter_email));
        } else if (!baseActivity.isValidMail(binding.emailET.getText().toString().trim())) {
            showToast(getString(R.string.enter_valid_email));
        } else if (binding.confirmEmailET.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.please_enter_confirm_email));
        } else if (!binding.emailET.getText().toString().trim().equals(binding.confirmEmailET.getText().toString().trim())) {
            showToast(getString(R.string.email_does_not_match));
        } else if (binding.userNameET.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.please_enter_username));
        } else if (binding.passwordET.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.enter_the_password));
        } else if (binding.confirmPasswordET.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.enter_the_password));
        } else if (!binding.passwordET.getText().toString().trim().equals(binding.confirmPasswordET.getText().toString().trim())) {
            showToast(getString(R.string.password_does_not_match));
        } else if (countryID == 0) {
            showToast(getString(R.string.plz_select_country));
        } else if (binding.addressET.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.enter_address));
        } else if (binding.addressTwoET.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.enter_second_address));
        } else if (stateID == 0) {
            showToast(getString(R.string.enter_state));
        } else if (cityID == 0) {
            showToast(getString(R.string.please_enter_city));
        } else if (binding.zipCodeET.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.please_enter_zipcode));
        } else if (binding.phoneNumberET.getText().toString().trim().isEmpty()) {
            showToast(getString(R.string.please_enter_phone_number));
        } else if (binding.questionsLL.que1ET.getText().toString().trim().isEmpty() || binding.questionsLL.ques2ET.getText().toString().trim().isEmpty() || binding.questionsLL.ques3ET.getText().toString().trim().isEmpty() || binding.questionsLL.ans1ET.getText().toString().trim().isEmpty() || binding.questionsLL.ans2ET.getText().toString().trim().isEmpty() || binding.questionsLL.ans3ET.getText().toString().trim().isEmpty()) {
            showToast(baseActivity.getString(R.string.plz_enter_questions));
        } else if (!binding.gedCB.isChecked() && !binding.highSchoolCB.isChecked() && !binding.collegeCB.isChecked()) {
            showToast(getString(R.string.please_select_education));
        } else if (binding.certificatesET.getText().toString().trim().isEmpty()) {
            showToast(baseActivity.getString(R.string.plz_enter_crtification_license));
        } else {
            return true;
        }
        return false;
    }

    private String getEducationData() {
        String educationdata = "";
        if (binding.gedCB.isChecked()) {
            educationdata = educationdata + "," + Const.EDUCATION_LEVEL_GED;
        }
        if (binding.collegeCB.isChecked()) {
            educationdata = educationdata + "," + Const.EDUCATION_LEVEL_COLLEGE_AND_ABOVE;
        }

        if (binding.highSchoolCB.isChecked()) {
            educationdata = educationdata + "," + Const.EDUCATION_LEVEL_HIH_SCHOOL;
        }
        if (educationdata.length() > 2) {
            return educationdata.substring(1);
        } else {
            return educationdata;
        }
    }

    private void gotoPaymentFragment() {
        if (binding.maleRB.isChecked()) {
            gender = Const.MALE;
        } else if (binding.femaleRB.isChecked()) {
            gender = Const.FEMALE;
        } else if (binding.otherRB.isChecked()) {
            gender = Const.OTHER;
        }

        ProfileData profileData = new ProfileData();
        profileData.first_name = binding.firstNameET.getText().toString().trim();
        profileData.last_name = binding.lastNameET.getText().toString().trim();
        profileData.email = binding.emailET.getText().toString().trim();
        profileData.username = binding.userNameET.getText().toString().trim();
        profileData.password = binding.passwordET.getText().toString().trim();
        profileData.contact_no = binding.phoneNumberET.getText().toString().trim();
        profileData.address = binding.addressET.getText().toString().trim();
        profileData.address_two = binding.addressTwoET.getText().toString().trim();
        profileData.country = binding.countryET.getText().toString().trim();
        profileData.city = binding.cityET.getText().toString().trim();
        profileData.zipcode = binding.zipCodeET.getText().toString().trim();
        profileData.gender = gender;
        profileData.city_state = binding.stateET.getText().toString().trim();
        profileData.security_question = getSecurityQueJson();
        profileData.education_level = getEducationData();
        profileData.certification = binding.certificatesET.getText().toString().trim();
        profileData.countryIds = countryID;
        profileData.stateIds = stateID;
        profileData.cityIds = cityID;


        Fragment fragment = new PaymentFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("signupData", profileData);
        fragment.setArguments(bundle);
        baseActivity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_frame, fragment)
                .addToBackStack(null)
                .commit();
    }

    private JSONArray getSecurityQueJson() {


        try {
            JSONArray complete_json = new JSONArray();

            JSONObject object = new JSONObject();
            object.put("question", binding.questionsLL.que1ET.getText().toString().trim());
            object.put("answer", binding.questionsLL.ans1ET.getText().toString().trim());
            complete_json.put(object);
            JSONObject object1 = new JSONObject();
            object1.put("question", binding.questionsLL.ques2ET.getText().toString().trim());
            object1.put("answer", binding.questionsLL.ans2ET.getText().toString().trim());
            complete_json.put(object1);
            JSONObject object2 = new JSONObject();
            object2.put("question", binding.questionsLL.ques3ET.getText().toString().trim());
            object2.put("answer", binding.questionsLL.ans3ET.getText().toString().trim());
            complete_json.put(object2);


            return complete_json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public void setResidenceData(Integer id, String title) {

        Log.e("setResidenceData", "id==> " + id + " title==>> " + title);

        baseActivity.hideSoftKeyboard();
        if (countryDialog != null && countryDialog.isShowing()) {
            countryDialog.dismiss();
        }

        Log.e("getDataCountryIds", "==> " + type + " ==Country==>> " + Const.COUNTRY +
                " ==State==>> " + Const.STATE +
                " ==City==>> " + Const.CITY);

        if (type == Const.COUNTRY) {
            binding.countryET.setText(title);
            binding.stateET.setText(baseActivity.getString(R.string.state));
            binding.cityET.setText(baseActivity.getString(R.string.city));
            countryID = id;
            stateID = 0;
            cityID = 0;
        } else if (type == Const.STATE)

        {
            binding.stateET.setText(title);
            binding.cityET.setText(baseActivity.getString(R.string.city));
            stateID = id;
            cityID = 0;
        } else {
            binding.cityET.setText(title);
            cityID = id;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Const.LOCATION_CODE) {
            if (resultCode == BaseActivity.RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(baseActivity, data);
                String placeName = place.getName().toString();
                if (placeName.contains("null")) {
                    placeName = placeName.replaceAll("null", "");
                }

                binding.addressET.setText(placeName);

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(baseActivity, data);
                showToast(status.toString());
            }
        }
    }

}