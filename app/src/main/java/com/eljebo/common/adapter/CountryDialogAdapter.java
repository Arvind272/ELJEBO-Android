package com.eljebo.common.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eljebo.common.data.CountryData;
import com.eljebo.common.dialog.CountryDialog;
import com.eljebo.common.utils.AdapterListener;
import com.eljebo.databinding.AdapterCountryDialogBinding;
import com.eljebo.serviceprovider.new_bean.CountryDialogListDataBean;

import java.util.ArrayList;

/**
 * Created by TOXSL\vinay.goyal on 16/6/18.
 */

public class CountryDialogAdapter extends RecyclerView.Adapter<CountryDialogAdapter.ViewHolder> {

    private CountryDialog countryDialog;
    private ArrayList<CountryData> countryDataArrayList;
    private Fragment fragment;
    private AdapterListener adapterListener;
    private ArrayList<CountryDialogListDataBean> countryDialogListDataBeans;

    public CountryDialogAdapter(CountryDialog countryDialog, ArrayList<CountryData> countryDataArrayList,
                                Fragment fragment, AdapterListener adapterListener,
                                ArrayList<CountryDialogListDataBean> countryDialogListDataBeans) {
        this.countryDialog = countryDialog;
        this.countryDataArrayList = countryDataArrayList;
        this.fragment = fragment;
        this.adapterListener = adapterListener;
        this.countryDialogListDataBeans = countryDialogListDataBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        AdapterCountryDialogBinding binding = AdapterCountryDialogBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.binding.countryTV.setText(countryDialogListDataBeans.get(position).getName());
        if (position + 1 == countryDataArrayList.size()) {
            adapterListener.onEndList();
        }
    }

    @Override
    public int getItemCount() {
        return countryDialogListDataBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        AdapterCountryDialogBinding binding;

        public ViewHolder(AdapterCountryDialogBinding itemView) {
            super(itemView.getRoot());

            this.binding = itemView;
            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    countryDialog.setData(getAdapterPosition());

                }
            });
        }
    }
}
