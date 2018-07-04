package com.eljebo.serviceprovider.fragment;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.eljebo.R;
import com.eljebo.common.adapter.ServiceAdapter;
import com.eljebo.common.data.SubService;
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.customer.custom_video_call.VideoCallCustomerActivity;
import com.eljebo.databinding.FragmentServiceUserDetailBinding;
import com.eljebo.serviceprovider.activity.MainActivity;

/**
 * Created by TOXSL\vinay.goyal on 12/6/18.
 */

public class ServiceUserDetailFragment extends BaseFragment {

    private View view;
    private FragmentServiceUserDetailBinding binding;
    private ServiceAdapter serviceAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_service_user_detail, container, false);
            return binding.getRoot();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((MainActivity) baseActivity).setTitle(getString(R.string.jack_thomas));
        this.view = view;
        baseActivity.hideSoftKeyboard();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.questionsLL.servicesRV.setLayoutManager(linearLayoutManager);

        if (baseActivity.store.containValue("selectedServices")) {
            serviceAdapter = new ServiceAdapter(this, baseActivity.store.<SubService>getData("selectedServices"));
            binding.questionsLL.servicesRV.setAdapter(serviceAdapter);
        } else {
            binding.questionsLL.cleanerLL.setVisibility(View.VISIBLE);
        }
        initUI();
    }

    private void initUI() {

        binding.questionsLL.serviceLL.setVisibility(View.VISIBLE);
        binding.checkInCTV.setOnClickListener(this);
        binding.timerOnIV.setOnClickListener(this);
        binding.customTextViewCallBtn.setOnClickListener(this);
        binding.customTextViewMsgBtn.setOnClickListener(this);
        binding.customTextViewVideoCallBtn.setOnClickListener(this);

        /*binding.customTextViewCallBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phoneNumber = "5454688854";
                Intent intent = new Intent(Intent.ACTION_CALL,
                        Uri.parse("tel:" + phoneNumber));
                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }
        });

        binding.customTextViewMsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "Testin message";
                Intent intent = new Intent(Intent.CATEGORY_APP_MESSAGING,
                        Uri.parse("tel:" + phoneNumber));
                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.SEND_RESPOND_VIA_MESSAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(intent);
            }
        });*/
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);

        switch (v.getId()) {

            case R.id.customTextViewVideoCallBtn:

                /*Intent intentVideo = new Intent(getActivity(), VideoCallCustomerActivity.class);
                startActivity(intentVideo);*/
                break;

            case R.id.checkInCTV:
                gotoCheckInFragment();
                break;

            case R.id.timerOnIV:
                gotoCheckInFragment();
                break;

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
                /*Intent sendIntent = new Intent(Intent.ACTION_VIEW);
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
                }*/
                break;
        }
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

    private void gotoCheckInFragment() {

        baseActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new CheckInFragment())
                .addToBackStack(null)
                .commit();
    }
}
