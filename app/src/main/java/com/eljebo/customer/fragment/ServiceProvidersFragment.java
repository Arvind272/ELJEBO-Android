package com.eljebo.customer.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eljebo.R;
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.customer.activity.CustomerMainActivity;
import com.eljebo.customer.adapter.ServiceProvidersAdapter;
import com.eljebo.databinding.FragmentServiceProvidersBinding;

/**
 * Created by TOXSL\vinay.goyal on 12/6/18.
 */

public class ServiceProvidersFragment extends BaseFragment {

    private View view;
    private FragmentServiceProvidersBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (view != null) {
            return view;
        } else {
            binding = DataBindingUtil.inflate(inflater, R.layout.fragment_service_providers, container, false);
            return binding.getRoot();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ((CustomerMainActivity) baseActivity).setTitle(getString(R.string.list_of_service_providers));
        this.view = view;

        initUI();
    }

    private void initUI() {
        binding.serviceProviderRV.setLayoutManager(new LinearLayoutManager(baseActivity));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.serviceProviderRV.getContext(), LinearLayoutManager.VERTICAL);
        binding.serviceProviderRV.addItemDecoration(dividerItemDecoration);
        ServiceProvidersAdapter serviceProvidersAdapter = new ServiceProvidersAdapter(this);
        binding.serviceProviderRV.setAdapter(serviceProvidersAdapter);
    }

    public void setClick(int pos) {
        baseActivity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.customer_container, new ServiceProviderLocationFragment())
                .addToBackStack(null)
                .commit();
    }
}
