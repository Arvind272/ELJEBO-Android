package com.eljebo.common.adapter;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CompoundButton;

import com.eljebo.R;
import com.eljebo.common.activity.BaseActivity;
import com.eljebo.common.data.ServiceData;
import com.eljebo.common.data.SubService;
import com.eljebo.customer.fragment.CustomerHomeFragment;
import com.eljebo.databinding.AdapterChildServiceBinding;
import com.eljebo.databinding.AdapterGroupServiceBinding;
import com.eljebo.serviceprovider.fragment.SignupFragment;

import java.util.ArrayList;


/**
 * Created by TOXSL\vinay.goyal on 16/6/18.
 */

public class ExpandableServiceAdapter extends BaseExpandableListAdapter {
    BaseActivity baseActivity;
    ArrayList<ServiceData> datas;
    Fragment fragment;

    public ExpandableServiceAdapter(BaseActivity baseActivity, ArrayList<ServiceData> datas, Fragment fragment) {
        this.baseActivity = baseActivity;
        this.datas = datas;
        this.fragment = fragment;
    }

    @Override
    public int getGroupCount() {
        return datas.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return datas.get(i).subServices.size();
    }

    @Override
    public ServiceData getGroup(int i) {
        return datas.get(i);
    }

    @Override
    public SubService getChild(int i, int i1) {
        return datas.get(i).subServices.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        AdapterGroupServiceBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),
                R.layout.adapter_group_service, viewGroup, false);


        Log.e("getGroupData", "==> " + getGroup(i).title);

        binding.headerServicesTV.setText(getGroup(i).title);

        if (getGroup(i).isExpand) {
            binding.headerServicesTV.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_uparw, 0);

        } else {
            binding.headerServicesTV.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.ic_dwnarw, 0);

        }
        return binding.getRoot();
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        AdapterChildServiceBinding childBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.adapter_child_service, viewGroup, false);
        childBinding.titleTV.setText(getChild(i, i1).title);

        Log.e("getChildData", "==> " + getChild(i, i1).title);

        childBinding.cleanerCB.setOnCheckedChangeListener(new check(i, i1) {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (fragment instanceof SignupFragment) {
                    ((SignupFragment) fragment).checkServiceData(group, child);
                } else if (fragment instanceof CustomerHomeFragment) {
                    ((CustomerHomeFragment) fragment).checkServiceData(group, child);

                }
            }
        });
        return childBinding.getRoot();
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    abstract class check implements CompoundButton.OnCheckedChangeListener {
        int group;
        int child;

        check(int i, int i1) {
            this.group = i;
            this.child = i1;
        }
    }

}
