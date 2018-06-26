package com.eljebo.serviceprovider.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import com.eljebo.R;
import com.eljebo.common.activity.BaseActivity;
import com.eljebo.serviceprovider.data.DrawerData;

/**
 * Created by TOXSL\neeraj.narwal on 3/12/16.
 */
public class DrawerAdapter extends ArrayAdapter<DrawerData>{
    BaseActivity activity;

    public DrawerAdapter(BaseActivity context, int resource, ArrayList<DrawerData> objects) {
        super(context, resource, objects);
        this.activity = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = newView(parent);
        }
        bindView(position, convertView);
        return (convertView);
    }

    private View newView(ViewGroup parent) {
        return (activity.getLayoutInflater().inflate(R.layout.adapter_drawer, parent, false));
    }

    private void bindView(int position, View convertView) {
        ImageView iconIV = (ImageView) convertView.findViewById(R.id.iconIV);
        TextView nameTV = (TextView) convertView.findViewById(R.id.nameTV);
        DrawerData data = getItem(position);
        iconIV.setImageResource(data.icon);
        nameTV.setText(data.name);
    }
}
