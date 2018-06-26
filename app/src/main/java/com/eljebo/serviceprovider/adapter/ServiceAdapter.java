package com.eljebo.serviceprovider.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.eljebo.R;
import com.eljebo.common.activity.BaseActivity;
import com.eljebo.common.data.SubService;
import com.eljebo.databinding.AdapterServiceBinding;

import java.util.ArrayList;

/**
 * Created by TOXSL\harleen.dutt on 15/6/18.
 */

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {
    private BaseActivity baseActivity;
    private ArrayList<SubService> datas;

    public ServiceAdapter(BaseActivity baseActivity, ArrayList<SubService> data) {
        this.baseActivity = baseActivity;
        this.datas = data;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterServiceBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_service, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.binding.titleTV.setText(datas.get(position).title);
        holder.binding.priceET.addTextChangedListener(new watcher(position, holder) {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                datas.get(position).price = holder.binding.priceET.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AdapterServiceBinding binding;

        public ViewHolder(AdapterServiceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    abstract class watcher implements TextWatcher {
        int position;
        ViewHolder holder;

        watcher(int position, ViewHolder holder) {
            this.position = position;
            this.holder = holder;
        }
    }
}
