package com.eljebo.customer.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eljebo.customer.fragment.ServiceProvidersFragment;
import com.eljebo.databinding.AdapterServiceProviderBinding;

/**
 * Created by TOXSL\vinay.goyal on 12/6/18.
 */

public class ServiceProvidersAdapter extends RecyclerView.Adapter<ServiceProvidersAdapter.ViewHolder> {

    private ServiceProvidersFragment serviceProvidersFragment;

    public ServiceProvidersAdapter(ServiceProvidersFragment serviceProvidersFragment) {
        this.serviceProvidersFragment = serviceProvidersFragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        AdapterServiceProviderBinding binding = AdapterServiceProviderBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.binding.serviceProviderRL.setTag(position);
        holder.binding.serviceProviderRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                serviceProvidersFragment.setClick(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private AdapterServiceProviderBinding binding;

        public ViewHolder(AdapterServiceProviderBinding itemView) {
            super(itemView.getRoot());

            this.binding = itemView;
        }
    }
}
