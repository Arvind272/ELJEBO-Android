package com.eljebo.common.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.eljebo.common.data.SubService;
import com.eljebo.databinding.AdapterServicesBinding;

import java.util.ArrayList;

/**
 * Created by TOXSL\vinay.goyal on 19/6/18.
 */

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {

    private Fragment fragment;
    private ArrayList<SubService> selectedServices;


    public ServiceAdapter(Fragment fragment, ArrayList<SubService> selectedServices) {
        this.fragment = fragment;
        this.selectedServices = selectedServices;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        AdapterServicesBinding binding = AdapterServicesBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.binding.serviceName.setText(selectedServices.get(position).title);
        Log.e("getServicePrice", "==> " +selectedServices.get(position).price);
        if (selectedServices.get(position).price.isEmpty()) {
            holder.binding.serviceChargeTV.setText("$0");
        } else {
            holder.binding.serviceChargeTV.setText("$" + selectedServices.get(position).price);
        }
    }

    @Override
    public int getItemCount() {
        return selectedServices.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private AdapterServicesBinding binding;

        public ViewHolder(AdapterServicesBinding itemView) {
            super(itemView.getRoot());

            this.binding = itemView;
        }
    }
}
