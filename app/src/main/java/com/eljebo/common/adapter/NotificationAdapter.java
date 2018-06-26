package com.eljebo.common.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;


import com.eljebo.R;
import com.eljebo.databinding.AdapterNotificationLayoutBinding;

import java.util.ArrayList;

/**
 * Created by TOXSL\himanshu.gulati on 19/4/18.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private ArrayList<String> arrayList;

    public NotificationAdapter(ArrayList<String> arrayList) {
        this.arrayList = arrayList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterNotificationLayoutBinding binding = DataBindingUtil.
                inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.adapter_notification_layout, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 10;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        AdapterNotificationLayoutBinding binding;

        ViewHolder(AdapterNotificationLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
