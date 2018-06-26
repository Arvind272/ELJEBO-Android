package com.eljebo.common.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.eljebo.R;
import com.eljebo.common.utils.Const;
import com.eljebo.customer.activity.CustomerMainActivity;
import com.eljebo.databinding.FragmentListBinding;
import com.eljebo.serviceprovider.activity.MainActivity;
import com.eljebo.common.adapter.NotificationAdapter;

import java.util.ArrayList;

/**
 * Created by TOXSL\himanshu.gulati on 19/4/18.
 */

public class NotificationFragment extends BaseFragment {
    private View view;
    private FragmentListBinding binding;
    private ArrayList<String> arrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(
                    inflater, R.layout.fragment_list, container, false);
            return binding.getRoot();
        }

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;

        if (store.getInt("role") == Const.ROLE_USER) {
            ((CustomerMainActivity) baseActivity).setTitle(getString(R.string.notifications));
        } else {
            ((MainActivity) baseActivity).setTitle(getString(R.string.notifications));
        }
        initUI();

    }

    private void initUI() {
        binding.listRV.setLayoutManager(new LinearLayoutManager(baseActivity));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.listRV.getContext(), LinearLayoutManager.VERTICAL);
        binding.listRV.addItemDecoration(dividerItemDecoration);
        binding.listRV.setAdapter(new NotificationAdapter(arrayList));
    }


}