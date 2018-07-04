package com.eljebo.customer.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.eljebo.R;
import com.eljebo.common.adapter.ServiceAdapter;
import com.eljebo.common.data.SubService;
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.customer.activity.CustomerMainActivity;
import com.eljebo.databinding.FragmentServiceProviderDetailBinding;
import com.eljebo.serviceprovider.video_service_call.NewVideoCallingServiceProActivity;
import com.eljebo.serviceprovider.video_service_call.VideoActivity;

/**
 * Created by TOXSL\vinay.goyal on 14/6/18.
 */

public class ServiceProviderDetailFragment extends BaseFragment {

    private View view;
    private FragmentServiceProviderDetailBinding binding;
    private Bundle bundle;
    private ServiceAdapter serviceAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_service_provider_detail, container, false);
            return binding.getRoot();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.view = view;
        baseActivity.hideSoftKeyboard();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.questionsLL.servicesRV.setLayoutManager(linearLayoutManager);
        if (baseActivity.store.containValue("selectedServices")) {
            serviceAdapter = new ServiceAdapter(this,
                    baseActivity.store.<SubService>getData("selectedServices"));
            binding.questionsLL.servicesRV.setAdapter(serviceAdapter);
        } else {
            binding.questionsLL.cleanerLL.setVisibility(View.VISIBLE);
        }
        baseActivity.hideSoftKeyboard();
        binding.questionsLL.availabilityLL.setVisibility(View.VISIBLE);
        ((CustomerMainActivity) baseActivity).setTitle(getString(R.string.jack_thomas));
        init();
    }

    private void init() {

        bundle = getArguments();
        if (bundle.getString("duration") != null) {
            binding.questionsLL.duration.setText(bundle.getString("duration") + " min");
        } else {
            binding.questionsLL.duration.setText(getString(R.string._1_mins));
        }
        binding.questionsLL.educationLL.setVisibility(View.VISIBLE);
        binding.questionsLL.availabilityLL.setVisibility(View.VISIBLE);
        binding.questionsLL.certificationLL.setVisibility(View.VISIBLE);
        binding.startTV.setOnClickListener(this);
        binding.timerOnIV.setOnClickListener(this);
        binding.customTextViewVideoCall.setOnClickListener(this);

        binding.customTextViewMsgBtn.setOnClickListener(this);
        binding.customTextViewCallBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {

            case R.id.customTextViewCallBtn:
                String phoneNumber = "5454688854";
                Intent intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:" + phoneNumber));

                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE},1);
                }
                else
                {
                    startActivity(intent);
                }
                break;

            case R.id.customTextViewMsgBtn:

                showMsgDialog();
                break;

            case R.id.customTextViewVideoCall:{

               /* Intent intent = new Intent(getActivity(), VideoActivity.class);
                startActivity(intent);*/
                Intent intentVideo = new Intent(getActivity(), VideoActivity.class);
                startActivity(intentVideo);

                break;
            }

            case R.id.startTV:
                gotoCustomerCheckInFragment();
                break;

            case R.id.timerOnIV:
                gotoCustomerCheckInFragment();
                break;
        }
    }

    private void gotoCustomerCheckInFragment() {
        bundle = getArguments();
        CustomerCheckInFragment customerCheckInFragment = new CustomerCheckInFragment();
        customerCheckInFragment.setArguments(bundle);
        baseActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.customer_container, customerCheckInFragment)
                .addToBackStack(null)
                .commit();
    }

    public void showMsgDialog(){
        final Dialog dialog = new Dialog(getActivity());
        /*binding = DataBindingUtil.inflate(LayoutInflater.from(getContext())
                , R.layout.show_msg_dialog_dialog, false);*/
        dialog.setContentView(R.layout.show_msg_dialog_dialog);
        dialog.setCanceledOnTouchOutside(false);

        Button btnOk = (Button) dialog.findViewById(R.id.btnOk);
        Button btnCancel = (Button)dialog.findViewById(R.id.btnCancel);

        // if button is clicked, close the custom dialog
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                // sendIntent.setData(Uri.parse("sms:"));
                sendIntent.setType("vnd.android-dir/mms-sms");
                sendIntent.putExtra("address", "5454688854");
                sendIntent.putExtra("sms_body","Body of Message");

                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CALL_PHONE},1);
                }
                else {
                    startActivity(sendIntent);
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();

    }

}
