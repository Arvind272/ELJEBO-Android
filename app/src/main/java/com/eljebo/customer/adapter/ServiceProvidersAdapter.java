package com.eljebo.customer.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.eljebo.R;
import com.eljebo.customer.cus_new_bean.ServiceProviderListBean;
import com.eljebo.customer.fragment.ServiceProvidersFragment;
import com.eljebo.databinding.AdapterServiceProviderBinding;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by TOXSL\vinay.goyal on 12/6/18.
 */

public class ServiceProvidersAdapter extends RecyclerView.Adapter<
        ServiceProvidersAdapter.ViewHolder> {

    private ServiceProvidersFragment serviceProvidersFragment;
    private Context context;
    private ArrayList<ServiceProviderListBean> serviceProviderListBeans;

    public ServiceProvidersAdapter(ServiceProvidersFragment serviceProvidersFragment,
                                   ArrayList<ServiceProviderListBean> serviceProviderListBeans,
                                   Context context) {
        this.serviceProvidersFragment = serviceProvidersFragment;
        this.serviceProviderListBeans = serviceProviderListBeans;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        AdapterServiceProviderBinding binding = AdapterServiceProviderBinding.inflate(layoutInflater,
                parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.binding.serviceProviderRL.setTag(position);
        holder.binding.serviceProviderRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                serviceProvidersFragment.setClick(pos);
            }
        });


        holder.binding.customTextServiceProName.setText(""+serviceProviderListBeans.get(position).getName());
        Log.e("serviceProvi", "serviceProviderListBeans==>" +
                serviceProviderListBeans.get(position).getName());

        holder.binding.serviceProviderCIV.setBorderColor(Color.parseColor("#DCDCDC"));
        holder.binding.serviceProviderCIV.setBorderWidth(2);

        holder.binding.progressBarLoader.setVisibility(View.VISIBLE);
        holder.binding.serviceProviderCIV.setVisibility(View.VISIBLE);

        Picasso.with(context)
                .load(serviceProviderListBeans.get(position).getProfile_pic())
                .placeholder(R.mipmap.ic_pic_home)
                .error(R.mipmap.ic_pic_home)
                .into(holder.binding.serviceProviderCIV, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.binding.progressBarLoader.setVisibility(View.GONE);
                    }
                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return serviceProviderListBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private AdapterServiceProviderBinding binding;

        public ViewHolder(AdapterServiceProviderBinding itemView) {
            super(itemView.getRoot());

            this.binding = itemView;
        }
    }
}
