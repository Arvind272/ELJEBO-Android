package com.eljebo.serviceprovider.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.TimePicker;

import com.eljebo.R;
import com.eljebo.common.activity.BaseActivity;
import com.eljebo.common.activity.LoginSignUpActivity;
import com.eljebo.common.adapter.ExpandableServiceAdapter;
import com.eljebo.common.data.ProfileData;
import com.eljebo.common.data.ServiceData;
import com.eljebo.common.data.SubService;
import com.eljebo.common.dialog.CountryDialog;
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.common.utils.Const;
import com.eljebo.common.utils.CustomEditText;
import com.eljebo.common.utils.ImageUtils;
import com.eljebo.customer.fragment.PaymentFragment;
import com.eljebo.databinding.FragmentServiceProviderSignupBinding;
import com.eljebo.serviceprovider.adapter.CustomCameraImagesAdapter;
import com.eljebo.serviceprovider.adapter.ServiceAdapter;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;


public class SignupFragment extends BaseFragment implements View.OnClickListener, ImageUtils.ImageSelectCallback, BaseActivity.PermCallback {
    ArrayList<String> imageList = new ArrayList<>();
    CustomCameraImagesAdapter imagesAdapter;
    private View view;
    private FragmentServiceProviderSignupBinding binding;
    private Calendar datetime;
    private ServiceAdapter serviceAdapter;
    private ArrayList<SubService> selectedServiceData = new ArrayList<>();
    private int gender = 0;
    private int type = 0;
    private int cityID, countryID, stateID;
    private Dialog countryDialog;
    private ArrayList<ServiceData> serviceDatas = new ArrayList<>();
    private int REQUEST_GALLERY_CODE = 111;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(
                    inflater, R.layout.fragment_service_provider_signup, container, false);
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

    @Override
    public void onResume() {
        super.onResume();


    }

    private void initUI() {
        hitServicesApi();
        setServiceAdapter();
        binding.selectedServiceRV.setLayoutManager(new LinearLayoutManager(baseActivity));
        binding.selectedServiceRV.setVisibility(View.GONE);
        serviceListFunctionality();

        binding.maleRB.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/unicodearialr.ttf"));
        binding.femaleRB.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/unicodearialr.ttf"));
        binding.otherRB.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/unicodearialr.ttf"));
        binding.selectServiceCTV.setOnClickListener(this);
        binding.imageRV.setLayoutManager(new LinearLayoutManager(baseActivity, LinearLayoutManager.HORIZONTAL, false));
        binding.selectServiceCTV.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_services_signup, 0, R.mipmap.ic_dwnarw, 0);

        initListener();
    }

    //set expand service list functionality
    private void serviceListFunctionality() {
        binding.servicesELV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                for (int k = 0; k < serviceDatas.size(); k++) {
                    if (k != i) {
                        serviceDatas.get(k).isExpand = false;
                        binding.servicesELV.collapseGroup(k);
                    } else {
                        serviceDatas.get(k).isExpand = true;
                    }
                }
            }
        });
        binding.servicesELV.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                serviceDatas.get(i).isExpand = false;

            }
        });
    }


    //set selected service data on service charge amount
    private void setSelectedServiceAdapter() {
        binding.selectedServiceRV.setVisibility(View.VISIBLE);

        if (serviceAdapter == null) {
            serviceAdapter = new ServiceAdapter(baseActivity, selectedServiceData);
            binding.selectedServiceRV.setAdapter(serviceAdapter);
        } else {
            serviceAdapter.notifyDataSetChanged();
        }
    }


    private void initListener() {
        binding.cityET.setOnClickListener(this);
        binding.stateET.setOnClickListener(this);
        binding.countryET.setOnClickListener(this);
        binding.addressET.setOnClickListener(this);
        binding.nextBT.setOnClickListener(this);
        binding.uploadCertificationRL.setOnClickListener(this);
        binding.toET.setOnClickListener(this);
        binding.fromET.setOnClickListener(this);
        binding.maleRB.setOnCheckedChangeListener(this);
        binding.femaleRB.setOnCheckedChangeListener(this);
        binding.otherRB.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        baseActivity.hideSoftKeyboard();
        switch (v.getId()) {
            case R.id.nextBT:
                baseActivity.store.setData("selectedServices", selectedServiceData);
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
            case R.id.selectServiceCTV:

                if (binding.servicesELV.getVisibility() == View.GONE) {
                    binding.servicesELV.setVisibility(View.VISIBLE);
                    binding.selectServiceCTV.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_services_signup, 0, R.mipmap.ic_uparw, 0);
                } else {
                    binding.servicesELV.setVisibility(View.GONE);
                    binding.selectServiceCTV.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_services_signup, 0, R.mipmap.ic_dwnarw, 0);
                }
                break;

            case R.id.uploadCertificationRL:
                if (baseActivity.checkPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, Const.REQUEST_CODE, this)) {
                    if (imageList.size() < 4) {
                        showChooseDialog();
                    } else {
                        showToast(baseActivity.getString(R.string.max_image_limit));
                    }
                }
                break;

            case R.id.toET:
                baseActivity.hideSoftKeyboard();
                openClock(binding.toET);
                break;

            case R.id.fromET:
                baseActivity.hideSoftKeyboard();
                openClock(binding.fromET);
                break;
            case R.id.addressET:
                callGoogleSearch(Const.LOCATION_CODE);
                break;
        }
    }

    //show image select dialog
    private void showChooseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(baseActivity);
        builder.setTitle(baseActivity.getString(R.string.choose_image_source));
        builder.setItems(new CharSequence[]{"Gallery", "Camera"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        selectMultipleImagesFromGallery();
                        break;
                    case 1:
                        ImageUtils.ImageSelect.Builder builder = new ImageUtils.ImageSelect.Builder(baseActivity, SignupFragment.this, Const.REQUEST_CODE);
                        builder.onlyCamera(true).crop(false).start();
                        break;

                }
                dialog.dismiss();
            }
        });
        builder.show();


    }

    //code to select multiple image from gallery
    public void selectMultipleImagesFromGallery() {

        Intent intent = new Intent(baseActivity, MultiImageSelectorActivity.class);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, false);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 4);
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
        startActivityForResult(intent, REQUEST_GALLERY_CODE);
    }

    private void hitServicesApi() {
        baseActivity.syncManager.sendJsonToServer(Const.API_SERVICE_LIST, null, this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
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


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        baseActivity.hideSoftKeyboard();
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

    //add selected  service
    public void checkServiceData(int groupPostion, int childPosition) {
        serviceDatas.get(groupPostion).subServices.get(childPosition).isChecked = !serviceDatas.get(groupPostion).subServices.get(childPosition).isChecked;
        selectedServiceData.clear();
        for (int g = 0; g < serviceDatas.size(); g++) {
            for (int c = 0; c < serviceDatas.get(g).subServices.size(); c++) {
                if (serviceDatas.get(g).subServices.get(c).isChecked) {
                    SubService service = new SubService();
                    service.id = serviceDatas.get(g).subServices.get(c).id;
                    service.title = serviceDatas.get(g).subServices.get(c).title;
                    service.price = "";
                    selectedServiceData.add(service);
                }
            }
        }

        setSelectedServiceAdapter();
    }

    private void changeGender(RadioButton selected, RadioButton unselected, RadioButton unselected1) {
        selected.setChecked(true);
        unselected.setChecked(false);
        unselected1.setChecked(false);
    }


    @Override
    public void onImageSelected(String imagePath, int resultCode) {
        if (resultCode == Const.REQUEST_CODE) {
            imageList.add(imagePath);
            setImageAdapter();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_GALLERY_CODE) {
            try {

                for (int i = 0; i < data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT).size(); i++) {
                    if (imageList.size() < 4) {
                        imageList.add(i, data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT).get(i));
                    } else {
                        showToast(baseActivity.getString(R.string.max_image_limit));
                    }
                }
                binding.imageRV.setVisibility(View.VISIBLE);
                setImageAdapter();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == Const.LOCATION_CODE) {
            if (resultCode == BaseActivity.RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(baseActivity, data);
                String placeName = place.getName().toString();
                if (placeName.contains("null")) {
                    placeName = placeName.replaceAll("null", "");
                }

                binding.addressET.setText(placeName);
//                addressLT = place.getLatLng();

            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(baseActivity, data);
                showToast(status.toString());
            }
        }
    }


    private void setImageAdapter() {
        binding.imageRV.setVisibility(View.VISIBLE);

        if (imagesAdapter == null) {
            imagesAdapter = new CustomCameraImagesAdapter(baseActivity, imageList, this);
            binding.imageRV.setAdapter(imagesAdapter);
        } else {
            imagesAdapter.notifyDataSetChanged();
        }
    }


    private void gotoPaymentFragment() {
        if (binding.maleRB.isChecked()) {
            gender = Const.MALE;
        } else if (binding.femaleRB.isChecked()) {
            gender = 1;
        } else if (binding.otherRB.isChecked()) {
            gender = 2;
        }


        ProfileData profileData = new ProfileData();
        profileData.first_name = binding.firstNameET.getText().toString().trim();
        profileData.last_name = binding.lastNameET.getText().toString().trim();
        profileData.email = binding.emailET.getText().toString().trim();
        profileData.username = binding.userNameET.getText().toString().trim();
        profileData.password = binding.firstNameET.getText().toString().trim();
        profileData.contact_no = binding.phoneNumberET.getText().toString().trim();
        profileData.address = binding.addressET.getText().toString().trim();
        profileData.address_two = binding.addressTwoET.getText().toString().trim();
        profileData.country = binding.countryET.getText().toString().trim();
        profileData.city = binding.cityET.getText().toString().trim();
        profileData.availability_time_from = binding.fromET.getText().toString().trim();
        profileData.availability_time_to = binding.toET.getText().toString().trim();
        profileData.zipcode = binding.zipCodeET.getText().toString().trim();
        profileData.gender = gender;
        profileData.city_state = binding.stateET.getText().toString().trim();
        profileData.security_question = getSecurityQueJson();
        profileData.sub_services = getServicesJson();
        profileData.education_level = getEducationData();
        profileData.certification = binding.certificatesET.getText().toString().trim();
        profileData.multiImage = getImageJSON();

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

    private void openClock(CustomEditText customEditText) {
        datetime = Calendar.getInstance();
        int hour = datetime.get(Calendar.HOUR_OF_DAY);
        int minute = datetime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;

        mTimePicker = new TimePickerDialog(baseActivity, new timeListener(customEditText) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String am_pm = "";
                Calendar time = Calendar.getInstance();
                time.set(Calendar.HOUR_OF_DAY, selectedHour);
                time.set(Calendar.MINUTE, selectedMinute);
                customEditText.setText(baseActivity.changeDateFormatFromDate(time.getTime(), "HH:mm"));


            }
        }, hour, minute, true);
        mTimePicker.setTitle(getString(R.string.select_time));
        mTimePicker.show();
    }


    @Override
    public void permGranted(int resultCode) {
        showChooseDialog();
    }

    @Override
    public void permDenied(int resultCode) {

    }

    public void setResidenceData(Integer id, String title) {

        Log.e("setResidenceData", "id==> " +id+ " title==>> "+title);

        InputMethodManager im = (InputMethodManager) baseActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (im != null) {
            im.hideSoftInputFromWindow(baseActivity.getWindow().getDecorView().getWindowToken(), 0);
        }
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
        } else if (type == Const.STATE) {
            binding.stateET.setText(title);
            binding.cityET.setText(baseActivity.getString(R.string.city));
            stateID = id;
            cityID = 0;
        } else {
            binding.cityET.setText(title);
            cityID = id;
        }
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
        } else if (selectedServiceData.size() == 0) {
            showToast(baseActivity.getString(R.string.please_select_atleast_one_service));
        } else if (isServicePriceAdded()) {
            showToast(baseActivity.getString(R.string.please_enter_selected_service_price));
        } else if (binding.toET.getText().toString().trim().isEmpty()) {
            showToast(baseActivity.getString(R.string.please_enter_time));
        } else if (binding.fromET.getText().toString().trim().isEmpty()) {
            showToast(baseActivity.getString(R.string.please_enter_from_time));
        } else if (imageList.size() == 0) {
            showToast(getString(R.string.plz_upload_image));
        } else {
            return true;
        }
        return false;
    }


    public boolean isServicePriceAdded() {
        boolean isValid = false;
        for (int i = 0; i < selectedServiceData.size(); i++) {
            if (!selectedServiceData.get(i).price.trim().isEmpty()) {
                isValid = false;
            } else {
                isValid = true;
                break;
            }
        }
        return isValid;
    }

    @Override
    public void onSyncSuccess(String controller, String action, boolean status, JSONObject jsonObject) {
        super.onSyncSuccess(controller, action, status, jsonObject);
        try {
            if (jsonObject.getString("url").equals(Const.API_SERVICE_LIST)) {
                if (jsonObject.getInt("status") == Const.STATUSOK) {
                    serviceDatas.clear();
                    for (int k = 0; k < jsonObject.getJSONArray("detail").length(); k++) {
                        ServiceData serviceData = new Gson().fromJson(jsonObject.getJSONArray("detail").getJSONObject(k).toString(), ServiceData.class);
                        serviceDatas.add(serviceData);
                    }
                    setServiceAdapter();
                } else {
                    baseActivity.showToastOne(jsonObject.getString("error"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void setServiceAdapter() {
        ExpandableServiceAdapter adapter = new ExpandableServiceAdapter(baseActivity, serviceDatas, this);
        binding.servicesELV.setAdapter(adapter);
    }

    //on delete image
    public void datadeleted() {
        if (imageList.isEmpty()) {
            binding.imageRV.setVisibility(View.GONE);
        }

    }

    private JSONArray getImageJSON() {
        try {
            JSONArray complete_json = new JSONArray();
            for (int i = 0; i < imageList.size(); i++) {
                File imageFile = new File(imageList.get(i));
                complete_json.put(imageFile);
            }

            return complete_json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private JSONArray getServicesJson() {
        try {
            JSONArray complete_json = new JSONArray();
            for (int i = 0; i < selectedServiceData.size(); i++) {
                JSONObject object = new JSONObject();
                object.put("sub_service_id", selectedServiceData.get(i).id);
                object.put("charge_amount", selectedServiceData.get(i).price);
                complete_json.put(object);
            }

            return complete_json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    abstract class timeListener implements TimePickerDialog.OnTimeSetListener {
        CustomEditText customEditText;

        timeListener(CustomEditText customEditText) {
            this.customEditText = customEditText;
        }
    }


}