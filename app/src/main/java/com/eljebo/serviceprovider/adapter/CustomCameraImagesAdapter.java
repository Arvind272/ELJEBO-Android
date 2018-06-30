package com.eljebo.serviceprovider.adapter;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eljebo.R;
import com.eljebo.common.activity.BaseActivity;
import com.eljebo.common.fragment.BaseFragment;
import com.eljebo.common.utils.ImageUtils;
import com.eljebo.databinding.AdapterCustomCameraImagesBinding;
import com.eljebo.serviceprovider.fragment.SignupFragment;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by TOXSL\harleen.dutt on 16/6/18.
 */

public class CustomCameraImagesAdapter extends RecyclerView.Adapter<CustomCameraImagesAdapter.MyViewHolder> {
    private BaseActivity baseActivity;
    private ArrayList<String> datas;
    private Fragment fragment;

    public CustomCameraImagesAdapter(BaseActivity baseActivity, ArrayList<String> datas, BaseFragment fragment) {
        this.baseActivity = baseActivity;
        this.datas = datas;

        this.fragment = fragment;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterCustomCameraImagesBinding binding = DataBindingUtil.
                inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_custom_camera_images, parent, false);
        return new CustomCameraImagesAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.binding.crossIV.setVisibility(View.VISIBLE);
        Bitmap bitmap = ImageUtils.imageCompress(datas.get(position));
        File uploadImageFile = ImageUtils.bitmapToFile(bitmap, baseActivity);
        Picasso.with(baseActivity).load(uploadImageFile).into(holder.binding.imageFileIV);


        holder.binding.crossIV.setTag(position);
        holder.binding.crossIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) v.getTag();
                datas.remove(pos);
                notifyDataSetChanged();

                ((SignupFragment) fragment).datadeleted();
            }
        });


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private AdapterCustomCameraImagesBinding binding;

        public MyViewHolder(AdapterCustomCameraImagesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
